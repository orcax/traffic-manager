$(function() {
  
  $('#created').datetimepicker();
  
  $('.btn-deljob').click(function(e) {
    e.preventDefault();
    var data = {
      'job_id': $(this).attr('id')
    };
    $.post("job/delete", data, function(result) {
      if (result.isok) {
        window.location.href = 'job';
      }
      else {
        alert('无法删除该作业区！');
      }
    }, 'json');
  });
  
  $('#btn-addjob').click(function(e) {
    e.preventDefault();
    var roadway = [];
    $('input[name=roadway]:checked').each(function() {
      roadway.push($(this).val());
    });
    var data = {
      'created': $('#created').val(),   
      'weather': $('#weather').val(),
      'highway': $('#highway').val(),
      'road_section': $('#road-section').val(),
      'roadway': roadway.join('、'),
      'section': $('#section').val(),
      'type': $('#type').val(),
      'content': $('#content').val(),
      'warning': $('#warning').val(),
      'speed_limit': $('#speed-limit').val(),
      'transition_warning': $('#transition-warning').val(),
      'buffer_facility': $('#buffer-facility').val(),
      'section_length': $('#section-length').val()
    };
    $.post("job/save", data, function(result) {
      if (result.isok) {
        window.location.href = 'job';
      }
      else {
        alert('添加作业区错误！');
      }
    }, 'json');
  });
});