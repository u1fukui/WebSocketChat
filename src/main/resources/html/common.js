//グローバル変数にWebSocketの変数を定義
var ws;

//getElementByIdの別名を定義
function $(id){
  return document.getElementById(id);
}

//WebSocketが接続された時に，「send」ボタンが有効になる
function onOpenWebSocket(){
  $("send").addEventListener("click",sendMessage,false);
  dispMessage("connected");
}

//WebSocketが切断された時に，「send」ボタンを無効にする
function onCloseWebSocket(){
  $("send").removeEventListener("click",sendMessage,false);
  dispMessage("disconnected");
}

//接続先よりメッセージを受信した時に，空文字でなければ画面に表示する
function onMessageWebSocket(event){
  var msg=event.data;
  if(msg==""){return;}
  dispMessage("> "+msg);
}

//ウィンドウを閉じたり画面遷移した時にWebSokcetを切断する
function onUnload(){
  ws.close();
}

//画面にメッセージを表示する
//上に表示されるメッセージが最新となる
function dispMessage(msg){
  var elem=document.createElement("div");
  elem.appendChild(document.createTextNode(msg));
  if($("messages").hasChildNodes()){
    $("messages").insertBefore(elem,$("messages").firstChild);
  }else{
    $("messages").appendChild(elem);
  }
}

//メッセージ入力欄が空白でなければメッセージを送信する
function sendMessage(){
  var message=$("message").value;
  if(message==""){return;}
  ws.send(message);
  $("message").value="";
}

//初期化処理
function initial(){

  //HTTPSで接続されている場合，WebSocketもセキュアにする
  var protocol=(location.protocol=="https:")?"wss":"ws";

  //port番号も込みで取得
  var host=location.host;
  
  console.log(location.host);
  
  //接続先URLの組み立て
  var url=protocol+"://"+host+"/ws/";

  //WebSocketのインスタンス化
  ws=new WebSocket(url);

  //WebSocketのイベントの登録
  ws.addEventListener("open",onOpenWebSocket,false);
  ws.addEventListener("close",onCloseWebSocket,false);
  ws.addEventListener("message",onMessageWebSocket,false);

  //ウィンドウを閉じたり画面遷移した時にWebSokcetを切断する
  window.addEventListener("unload",onUnload,false);

}

//オンロード時のイベントに，初期化関数を定義
window.addEventListener("load",initial,false);
