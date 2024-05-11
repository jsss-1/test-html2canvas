package com.jsss.share.component;

import com.jsss.share.controller.ShareController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.io.File;
import java.util.Arrays;
import java.util.Date;

@Component
public class ImageCleanupTask {

    private static final Logger logger = LoggerFactory.getLogger(ShareController.class);


    @Value("${wk.image.storage}")
    String DIRECTORY_PATH;
    @Value("${wk.image.max_storage_size}")
    long MAX_STORAGE_SIZE;

    @Scheduled(cron = "0 0 0 * * *") // 每天凌晨执行
    public void cleanupImages() {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            return; // 目录不存在，直接返回
        }

        long totalSize = 0;

        File[] files = directory.listFiles();
        if (files == null) {
            return; // 目录为空，直接返回
        }

        // 按文件最后修改时间从大到小排序
        Arrays.sort(files, (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()));

        for (File file : files) {
            long fileSize = file.length();
            totalSize += fileSize;

            Date currentDate = new Date();
            Date fileDate = new Date(file.lastModified());
            boolean isBeforeToday = fileDate.before(currentDate);

            if (isBeforeToday || totalSize > MAX_STORAGE_SIZE) {
                file.delete();
                logger.info("删除图片: " + file.getName());
            }
        }
    }
}
