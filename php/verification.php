<?php

/**
 * @author Tenzing Sherpa
 * 
 */

require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if (isset($_POST['cipher']) && isset($_POST['code']) && isset($_POST['username']) && isset($_POST['mscal'])) {

    // receiving the post params
    $cipher = $_POST['cipher'];
    $code = $_POST['code'];
    $username = $_POST['username'];
    $mscal = $_POST['mscal'];

            
    // storing the verification details
    $user = $db->store_verification($cipher, $code,$username, $mscal);

    if ($user != false) {
        // use is found
        $response["error"] = FALSE;
        $response["uid"] = $user["user_name"];
       
        echo json_encode($response);
    } else {
        // user is not found with the credentials
        $response["error"] = TRUE;
        $response["error_msg"] = "Storing credentials are wrong. Please try again!";
        echo json_encode($response);
    }
} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters is missing!";
    echo json_encode($response);
}
?>

