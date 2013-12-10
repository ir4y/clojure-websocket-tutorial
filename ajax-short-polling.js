function doPoll(){
 $.post('ajax/test.html', function(data) {
  alert(data);  // process results here
  setTimeout(doPoll,5000);
  });
}
