<?php
	class db_connection{
		private $connection;

		function __constructor(){
			require_once('config.php');
			$this->connection = new mysqli(DB_HOST, DB_USERNAME, DB_PASSWORD, DB_NAME);
			if (mysqli_connect_errno()) {
				return null;
			}
			return $this->connection;
		}
	}
?>