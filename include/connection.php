<?php
/**
 * @Author: Manraj Singh
 * @Date:   2016-03-17 22:26:10
 * @Last Modified by:   Manraj Singh
 * @Last Modified time: 2016-03-17 22:26:22
 */
	class db_connection{
		private $connection;

		function __constructor(){
		}

		function connect(){
			require_once('config.php');
			$this->connection = new mysqli(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_NAME);
			if (mysqli_connect_errno()) {
				return null;
			}
			return $this->connection;
		}
	}
?>