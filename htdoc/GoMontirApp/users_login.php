<?php
  $con = mysql_connect('localhost','root','');
  mysql_select_db('go_montir');
 
  $sql = "select * from users where id='".$_GET['id_pelanggan']."'";
  $result = array();
  $res = mysql_query($sql);
  while($row = mysql_fetch_array($res)){
		array_push($result,
		array('id'=>$row["id"],
       'email'=>$row["email"],'password'=>$row["password"]
	));
}	

echo json_encode($result); 
  mysql_close($con);
 
?>
	