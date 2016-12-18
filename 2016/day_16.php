<?php
$input = "11100010111110100";

$disk_size = 35651584;

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
