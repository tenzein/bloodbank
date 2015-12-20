<?php
$con=mysqli_connect("localhost","root","","bloodbankplus");
// Check connection
if (mysqli_connect_error())
{
$response["success"] = 0;
 $response["message"] = "Database Error!";
die(json_encode($response));
    echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

    $status = $_POST["status"];
    $mobile = $_POST["number"];
    // $app_user_id =$_POST['app_user_id'];

    if($status =="success")
    {
        $sts =1;
        $sql = "UPDATE user SET isverified=$sts WHERE mobile_no='$mobile'";
      if(mysqli_query($con, $sql)){
            $response["error"] = FALSE;
            $response["error_msg"] = " Verification Status successful!";
            die(json_encode($response));
         } else {
            $response["error"] = TRUE;
             $response["error_msg"] = "Cannot Add";
            die(json_encode($response));
         }
    }else
    {
        $sts =0;
    }

    
  
 mysqli_close($con);
?>