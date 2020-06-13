<?php
//$_POST => array => $_POST['personne']
$f = fopen('prenom.txt','a'); //on ouvre le fichier
fwrite($f,$_POST['personne']); //on écrit sur le fichier
$f = fclose($f); //on ferme le fichier
$file = file_get_contents('prenom.txt', true);
echo $file;
