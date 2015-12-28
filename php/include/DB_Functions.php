<?php

/**
 * @author Tenzing Sherpa
 *  */

class DB_Functions {

    private $conn;

    // constructor
    function __construct() {
        require_once 'DB_Connect.php';
        // connecting to database
        $db = new Db_Connect();
        $this->conn = $db->connect();
        // $this->uid = $this->generateUniqueId();
    }

    // destructor
    function __destruct() {
        
    }

    public function encryptPassword($password)
    {
     $options = [
                    'cost'=> 11,
                    'salt' => mcrypt_create_iv(22,MCRYPT_DEV_URANDOM)
                    ];
    $hash = password_hash($password,PASSWORD_BCRYPT, $options);
    return $hash;   
    }

    public function genUserID($blood)
    {
          //generating unique id for user 
        $rand = rand(10000,99999);
        $bl = str_replace("+", "P", $blood);
        $bld =str_replace("-", "N", $bl);
        $uuid =$bld.$rand; 
        return $uuid;
    }
   
    /**
     * Storing new user
     * returns user details
     */
    public function storeUser($name, $lname, $username, $password, $dob, $blood, $gender,$phone, $email, $district,$street) {
        

        $uid = $this->genUserID($blood);
               $db = str_replace("/", "-", $dob);
        // generating the age of the user
        $tz = new DateTimeZone("Asia/Kathmandu");
        $age = DateTime::createFromFormat("Y-m-d",$db,$tz)->diff(new DateTime("now",$tz))->y;

        // generating password;
        $encrypted_password = $this->encryptPassword($password); // encrypted password
       
        $stmt = $this->conn->prepare("INSERT INTO user(user_id, first_name, last_name, user_name, password,   dob, age,
            blood_group,gender, mobile_no, email,joined_date)
         VALUES(?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, NOW())");
        $stmt->bind_param("ssssssissis", $uid, $name, $lname, $username, $encrypted_password,  $dob, $age, $blood, $gender,$phone ,$email);
        $result = $stmt->execute();
        $stmt->close();

// string location 
        $stmt =$this->conn->prepare("INSERT INTO location(user_id,district, main_location) VALUES(?,?,?)");
        $stmt->bind_param("sss",$uid,$district, $street);
        $result=$stmt->execute();
        $stmt->close();

        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM user WHERE user_name = ?");
            $stmt->bind_param("s", $username);
            $stmt->execute();
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $user;
        } else {
            return false;
        }
    }


    /**
     * Get user by email and password
     */
    public function getUserByEmailAndPassword($username, $password) {

        $stmt = $this->conn->prepare("SELECT * FROM user WHERE user_name = ?");

        $stmt->bind_param("s", $username);

        if ($stmt->execute()) {
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();
            return $user;
        } else {
            return NULL;
        }
    }

    /**
     * Check user is existed or not
     */
    public function isUserExisted($username) {
        $stmt = $this->conn->prepare("SELECT user_name from user WHERE user_name = ?");

        $stmt->bind_param("s", $username);

        $stmt->execute();

        $stmt->store_result();

        if ($stmt->num_rows > 0) {
            // user existed 
            $stmt->close();
            return true;
        } else {
            // user not existed
            $stmt->close();
            return false;
        }
    }


// check phone number is existed or not
    public function isNumberExisted($phone)
    {
        $stmt = $this->conn->prepare("SELECT mobile_no from user WHERE mobile_no = ?");

        $stmt->bind_param("s", $phone);

        $stmt->execute();

        $stmt->store_result();

        if ($stmt->num_rows > 0) {
            // user existed 
            $stmt->close();
            return true;
        } else {
            // user not existed
            $stmt->close();
            return false;
        }
    }

 // storing the verification of the number
    public function store_verification($cipher, $code,$username,$mscal)
    {
       
         // $uid = $this->genUserID($blood);
       
        $stmt =$this->conn->prepare("INSERT INTO mob_cognalys(user_name, cipher, verify_code,miscall_number) VALUES(?,?,?,?)");
        $stmt->bind_param("ssss",$username,$cipher, $code,$mscal);
        $result=$stmt->execute();
        $stmt->close();

        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM mob_cognalys WHERE user_name = ?");
            $stmt->bind_param("s", $username);
            $stmt->execute();
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $user;
        } else {
            return false;
        }
    }

// storing the user app id
 public function storeAppid($app_id,$username)
    {
       
         // $uid = $this->genUserID($blood);
       $us= $this->isUserExisted($username);
        $stmt =$this->conn->prepare("UPDATE mob_cognalys SET app_user_id =?");
        $stmt->bind_param("s",$app_id);
        $result=$stmt->execute();
        $stmt->close();

        // check for successful store
       if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM user WHERE user_name = ?");
            $stmt->bind_param("s", $us);
            $stmt->execute();
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $user;
        } else {
            return false;
        }
    }
// updating the verification status
    public function updateStatus($status,$fname)
    {

       $firstname = $fname;
        $stmt =$this->conn->prepare("UPDATE user SET isverified = $status WHERE 'first_name' = $firstname;");
        $result=$stmt->execute();
        $stmt->close();

        // check for successful store
        if ($result) {
            $stmt = $this->conn->prepare("SELECT * FROM user WHERE isverified = ?");
            $stmt->bind_param("i", $status);
            $stmt->execute();
            $user = $stmt->get_result()->fetch_assoc();
            $stmt->close();

            return $user;
        } else {
            return false;
        }   
    }

    /**
     * Encrypting password
     * @param password
     * returns salt and encrypted password
     */
    // public function hashSSHA($password) {

    //     $salt = sha1(rand());
    //     $salt = substr($salt, 0, 10);
    //     $encrypted = base64_encode(sha1($password . $salt, true) . $salt);
    //     $hash = array("salt" => $salt, "encrypted" => $encrypted);
    //     return $hash;
    // }

    // /**
    //  * Decrypting password
    //  * @param salt, password
    //  * returns hash string
    //  */
    // public function checkhashSSHA($salt, $password) {

    //     $hash = base64_encode(sha1($password . $salt, true) . $salt);

    //     return $hash;
    // }


    // storing location
      public function storeLocation($district, $street) {
      
        $stmt = $this->conn->prepare("INSERT INTO location(district, main_location)  VALUES(?, ?)");
        $stmt->bind_param("ss", $district, $street);
        if (!$stmt) {

            return $this->conn->errno . ": " . $this->conn->error;
        }
        $result = $stmt->execute();
        return $result;
        $stmt->close();

      
    }



}

?>

