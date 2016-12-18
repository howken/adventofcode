<?php
$input = "--INPUT--";

$disk_size = 0;

while(strlen($input) < $disk_size) {
    $a = $input;
    $b = strrev($a);
    $chars = str_split($b);
    $b = "";
    foreach($chars as $char) {
        $b .= ($char?"0":"1");
    }
    $input = $a."0".$b;
}

$checksum = substr($input, 0, $disk_size);

while(true) {
    $parts = str_split($checksum, 2);
    $tmp = "";
    foreach($parts as $part) {
        if ($part == "00" or $part == "11") {
            $tmp .= "1";
        } else {
            $tmp .= "0";
        }
    }
    $checksum = $tmp;
    if (strlen($checksum) % 2 != 0) {
        break;
    }
}

echo "result: $checksum \n";

?>
