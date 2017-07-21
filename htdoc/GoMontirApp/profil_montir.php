<?php
  $con = mysql_connect('localhost','root','');
  mysql_select_db('go_montir');
 
  $sql = "select * from montir where id_montir='".$_GET['id_montir']."'";
  $result = array();
  $res = mysql_query($sql);
  while($row = mysql_fetch_array($res)){
		array_push($result,
		array('id_montir'=>$row["id_montir"],
		'foto'=>$row["foto"],
       'nm_montir'=>$row["nm_montir"],
       'email'=>$row["email"],
       'alamat'=>$row["alamat"],
       'telepon'=>$row["telepon"]
	));
}	

echo json_encode($result); 
  mysql_close($con);
 
?>
	