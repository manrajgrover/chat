<?php
/**
 * @Author: Manraj Singh
 * @Date:   2016-03-16 21:44:12
 * @Last Modified by:   Manraj Singh
 * @Last Modified time: 2016-03-17 22:25:19
 */
	require 'libs/Slim/Slim.php';
	
	\Slim\Slim::registerAutoloader();
	$app = new \Slim\Slim();
	$app->get('/hello/:name', function ($name) {
		echo "Hello, " . $name;
	});
	$app->run();
?>