  function getXmlHttp() {
    var xmlhttp;
    try {
      xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
    } catch (e) {
    try {
      xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    } catch (E) {
      xmlhttp = false;
    }
    }
    if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
      xmlhttp = new XMLHttpRequest();
    }
    return xmlhttp;
  }

  function turnAlarmsOff() {
    var xmlhttp = getXmlHttp(); // Создаём объект XMLHTTP
            xmlhttp.open('GET', 'turnAlarmsOff', true); // Открываем асинхронное соединение
            xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // Отправляем кодировку
            xmlhttp.send();//id=" + encodeURIComponent(element.value)); // Отправляем POST-запрос
            xmlhttp.onreadystatechange = function() { // Ждём ответа от сервера
              if (xmlhttp.readyState == 4) { // Ответ пришёл
                if(xmlhttp.status == 200) { // Сервер вернул код 200 (что хорошо)
                  // Выводим ответ сервера
                }
              }
            };
  }

  function turnSwitchArduino(element) {
    //alert(element.value);
        var xmlhttp = getXmlHttp(); // Создаём объект XMLHTTP
        xmlhttp.open('POST', 'turnSwitchArduino', true); // Открываем асинхронное соединение
        xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // Отправляем кодировку
        xmlhttp.send("id=" + encodeURIComponent(element.value)); // Отправляем POST-запрос
        xmlhttp.onreadystatechange = function() { // Ждём ответа от сервера
          if (xmlhttp.readyState == 4) { // Ответ пришёл
            if(xmlhttp.status == 200) { // Сервер вернул код 200 (что хорошо)
              // Выводим ответ сервера
            }
          }
        };
  }

  function turnSwitchSonoff(element) {
        var xmlhttp = getXmlHttp(); // Создаём объект XMLHTTP
        xmlhttp.open('POST', 'turnSwitchSonoff', true); // Открываем асинхронное соединение
        xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // Отправляем кодировку
        xmlhttp.send("id=" + encodeURIComponent(element.value)); // Отправляем POST-запрос
        xmlhttp.onreadystatechange = function() { // Ждём ответа от сервера
          if (xmlhttp.readyState == 4) { // Ответ пришёл
            if(xmlhttp.status == 200) { // Сервер вернул код 200 (что хорошо)
              // Выводим ответ сервера
            }
          }
        };
  }

    function fillTempetureCanvas() {
    element = document.getElementById("tSensorCanvas");
    element.width = element.width;
          var xmlhttp = getXmlHttp(); // Создаём объект XMLHTTP
          xmlhttp.open('GET', 'getTempetureJSON', true); // Открываем асинхронное соединение
          xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded'); // Отправляем кодировку
          xmlhttp.send(null); // Отправляем POST-запрос
          xmlhttp.onreadystatechange = function() { // Ждём ответа от сервера
            if (xmlhttp.readyState == 4) { // Ответ пришёл
              if(xmlhttp.status == 200) { // Сервер вернул код 200 (что хорошо)
                var JSONresponseObj = JSON.parse(xmlhttp.responseText);
                var canvas_context = element.getContext("2d");
                canvas_context.lineWidth="3";
                canvas_context.strokeStyle="green";
                var rangeTemp = JSONresponseObj.range.tMax - JSONresponseObj.range.tMin
                JSONresponseObj.tempetureValues.forEach(function(item, i, arr) {
                    canvas_context.lineTo(i, 200 - 200*(item.temp-JSONresponseObj.range.tMin)/rangeTemp);
                });
                element.title = "T: " + JSONresponseObj.range.tMin + " - " + JSONresponseObj.range.tMax
                canvas_context.stroke();
              }
            }
          };
    }