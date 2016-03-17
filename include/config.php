<?php
/**
 * @Author: Manraj Singh
 * @Date:   2016-03-17 22:25:42
 * @Last Modified by:   Manraj Singh
 * @Last Modified time: 2016-03-17 22:26:04
 */
	$private = file_get_contents("../private/settings.json");
	$json = json_decode($private, true);
	define('DB_USERNAME', $json['username']);
	define('DB_PASSWORD', $json['password']);
	define('DB_HOST', $json['db_host']);
	define('DB_NAME', $json['db_name']);
	define("GCM_API", $json['gcm_api']);
?>