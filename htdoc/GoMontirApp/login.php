<?php
  $con = mysql_connect('localhost','root','');
  mysql_select_db('go_montir');
 
  $sql = "select * from users where email='".$_GET['email']."' and password='".$_GET['password']."'";
  $result = array();
  $res = mysql_query($sql);
  while($row = mysql_fetch_array($res)){
		array_push($result,
		array('id'=>$row["id"],
       'email'=>$row["email"],
       'username'=>$row["username"],
       'password'=>$row["password"],
       'telepon'=>$row["telepon"]
	));
}	

echo json_encode($result); 
  mysql_close($con);
 
?>
	