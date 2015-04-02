$(function() {

  $('#created').datetimepicker();

  $('.btn-delaccident').click(function(e) {
    e.preventDefault();
    var data = {
      'accident_id' : $(this).attr('id')
    };
    $.post("accident/delete", data, function(result) {
      if (result.isok) {
        window.location.href = 'accident';
      } else {
        alert('无法删除该事故！');
      }
    }, 'json');
  });

  $('#btn-addaccident').click(function(e) {
    e.preventDefault();
    var humanFactor = [], vehicleFactor = [], roadFactor = [], envFactor = [], jobFactor = [];
    $('input[name=human-factor]:checked').each(function() {
      humanFactor.push($(this).val());
    });
    $('input[name=vehicle-factor]:checked').each(function() {
      vehicleFactor.push($(this).val());
    });
    $('input[name=road-factor]:checked').each(function() {
      roadFactor.push($(this).val());
    });
    $('input[name=env-factor]:checked').each(function() {
      envFactor.push($(this).val());
    });
    $('input[name=job-factor]:checked').each(function() {
      jobFactor.push($(this).val());
    });
    var data = {
      'job_id' : $('#job-id').val(),
      'created' : $('#created').val(),
      'job_location' : $('#job-location').val(),
      'location' : $('#location').val(),
      'type' : $('#type').val(),
      'vehicle' : $('#vehicle').val(),
      'result' : $('#result').val(),
      'description' : $('#description').val(),
      'compensation' : $('#compensation').val(),
      'human_factor' : humanFactor.join('、'),
      'vehicle_factor' : vehicleFactor.join('、'),
      'road_factor' : roadFactor.join('、'),
      'env_factor' : envFactor.join('、'),
      'job_factor' : jobFactor.join('、')
    };
    $.post("accident/save", data, function(result) {
      if (result.isok) {
        window.location.href = 'accident';
      } else {
        alert('添加事故失败！');
      }
    }, 'json');
  });
});