<?php

/**
 * @author Tenzing Sherpa
 * 
 */

require_once 'include/DB_Functions.php';
$db = new DB_Functions();

// json response array
$response = array("error" => FALSE);

if (isset($_POST['name']) && isset($_POST['lname']) && isset($_POST['username']) && isset($_POST['password']) && isset($_POST['vpassword'])
    && isset($_POST['dob']) && isset($_POST['blood']) && isset($_POST['gender']) && isset($_POST['phone']) 
    && isset($_POST['email']) && isset($_POST['street'])  && isset($_POST['district'])) {

    // receiving the post params
    $name = $_POST['name'];
    $lname = $_POST['lname'];
    $username = $_POST['username'];
    $password = $_POST['password'];
    $vpassword =$_POST['vpassword'];
    $dob = $_POST['dob'];
        $blood = $_POST['blood'];
        $gender = $_POST['gender'];
        $phone = $_POST['phone'];
        $email =$_POST['email'];
    $street = $_POST['street'];
    $district =$_POST['district'];
    // $cipher = $_POST['cipher'];
    // $code = $_POST['code'];
        
        //  //generating unique id for user 
        // $rand = rand(10000,99999);
        // $bl = str_replace("+", "P", $blood);
        // $bld =str_replace("-", "N", $bl);
        // $uid =$bld.$rand; 
    
    // check if user is already existed with the same email
    if ($db->isUserExisted($username)) {
        // user already existed
        $response["error"] = TRUE;
        $response["error_msg"] = "User already existed with " . $username;
        echo json_encode($response);

    } else if($db->isNumberExisted($phone)) {
                        // user already existed
                        $response["error"] = TRUE;
                        $response["error_msg"] = "Phone Number already existed with " . $phone;
                        echo json_encode($response);
                    }else {
                                   
                                    // create a new user
                                    $user = $db->storeUser($name, $lname, $username, $password,  $dob, $blood, $gender, $phone, $email,$district,$street);
                                    // $sts = $db->verify($status);
                                      
                                      if ($user) {
                                        // user stored successfully
                                        $response["error"] = FALSE;
                                        $response["uid"] = $user["user_id"];
                                        $response["user"]["name"] = $user["first_name"];
                                        $response["user"]["username"] = $user["user_name"];
                                        $response["user"]["created_at"] = $user["joined_date"];
                                        // $response["user"]["updated_at"] = $user["updated_at"];
                                        echo json_encode($response);

                                    } else {
                                        // user failed to store
                                        $response["error"] = TRUE;
                                        $response["error_msg"] = "Unknown error occurred in registration!";
                                        echo json_encode($response);
                                    }


                        }
                        

} else {
    $response["error"] = TRUE;
    $response["error_msg"] = "Required parameters (name, email or password) is missing!";
    echo json_encode($response);
}
?>

