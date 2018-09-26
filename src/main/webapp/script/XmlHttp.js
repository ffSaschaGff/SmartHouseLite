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
                  alarm('ok'); // Выводим ответ сервера
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
              alarm('ok'); // Выводим ответ сервера
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
              alarm('ok'); // Выводим ответ сервера
            }
          }
        };
  }