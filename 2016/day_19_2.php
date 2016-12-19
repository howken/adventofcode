<?php

$input = 0;
$p = pow(3, ((int) log($input, 3)));
$elf = ($p == $input) ? $p : (($input < 2 * $p) ? $input - $p : 2 * $input - 3 * $p);
echo "last elf: $elf\n";

?>
