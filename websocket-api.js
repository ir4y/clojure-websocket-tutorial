var ws = new WebSocket("ws://example.com/update");
 
ws.onopen = function() { alert("Connection opened...") };
 
ws.onclose = function() { alert("Connection closed...") };
 
ws.onmessage = function(message) { 
    alert(message.data); 
};

ws.send("Hello world")
