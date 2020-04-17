function notifyMe() {
  if (!("Notification" in window)) {
    alert("This browser does not support desktop notification");
  } else if (Notification.permission === "granted") {
        sendNotification("text")
  }

  else if (Notification.permission !== "denied") {
    Notification.requestPermission().then(function (permission) {
      if (permission === "granted") {
        sendNotification("text")
      } else {
        alert("Cannot send Notification");
      }
    });
  }
}

function sendNotification(text){
    let notification = new Notification(text);
}