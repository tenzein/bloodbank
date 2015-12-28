<?php

/**
 * @author Tenzing Sherpa
 * 
 */

require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if (isset($_POST['username']) && isset($_POST['app_user_id'])) {

    // receiving the post params
    $username = $_POST['username'];
    $app_id = $_POST['app_user_id'];
    

            
                 // storing the verification details
                 $user = $db->storeAppid($app_id,$username);

                          if ($user===FALSE) {
                                        // user stored successfully
                                        $response["error"] = TRUE;
                                         $response["error_msg"] = "Unknown error occurred in registration!";
                                        
                                        echo json_encode($response);

                                    } else {
                                        // user failed to store
                                        $response["error"] = FALSE;

                                       $response["error_msg"] = "successfully registration";
                                        echo json_encode($response);
                                    }

} else {
    // required post params is missing
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters is missing!";
    echo json_encode($response);
}
?>

