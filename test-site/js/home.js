$(document).ready(function(){

	let datas=[
		{
			name: '1',
			value: '11'
		},
		{
			name: '2',
			value: '22'
		},
		{
			name: '3',
			value: '33'
		},
		{
			name: '4',
			value: '44'
		},

	];
	set_datas(datas);
});


function set_datas(datas){

	if (!datas) {
        return false;
    }

	// datas-list中现有内容
    $(".datas-list").empty();

    $.each(datas, function (i, data) {
        var tr = 
            '<tr>' +
                '<td>' + data.name + '</td>' +
                '<td>' + data.value + '</td>' +               
            '</tr>';
    
        $(".datas-list").append(tr);

    });

}