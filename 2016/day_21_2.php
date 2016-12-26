<?php

$file = fopen("input", "r") or die("Unable to open file!");
$input = [];

while(!feof($file)) {
    $input[] = trim(fgets($file));
}

fclose($file);

foreach(range('a', 'h') as $a) {
    foreach(range('a', 'h') as $b) {
        foreach(range('a', 'h') as $c) {
            foreach(range('a', 'h') as $d) {
                foreach(range('a', 'h') as $e) {
                    foreach(range('a', 'h') as $f) {
                        foreach(range('a', 'h') as $g) {
                            foreach(range('a', 'h') as $h) {
                                if (count( array_unique( str_split("$a$b$c$d$e$f$g$h"))) != 8) {
                                    continue;
                                }
                                $pwd = [$a, $b, $c, $d, $e, $f, $g, $h];

                                foreach($input as $row) {
                                    $insts = explode(" ", $row);
                                    if ($insts[0] == "swap" and $insts[1] == "position") {
                                        $tmp = $pwd[$insts[2]];
                                        $pwd[$insts[2]] = $pwd[$insts[5]];
                                        $pwd[$insts[5]] = $tmp;
                                    } else if ($insts[0] == "swap" and $insts[1] == "letter") {
                                        foreach($pwd as $key => $char) {
                                            if ($char == $insts[2]) {
                                                $pos1 = $key;
                                            } else if ($char == $insts[5]) {
                                                $pos2 = $key;
                                            }
                                        }
                                        $pwd[$pos1] = $insts[5];
                                        $pwd[$pos2] = $insts[2];
                                    } else if ($insts[0] == "reverse") {
                                        $length = (int) $insts[4] - (int) $insts[2];
                                        $reverse = array_slice($pwd, (int) $insts[2], $length+1);
                                        $begining = array_slice($pwd, 0, $insts[2]);
                                        $end = array_splice($pwd, ((int)$insts[4]+1));
                                        $pwd = array_merge($begining, array_reverse($reverse), $end);
                                    } else if ($insts[0] == "rotate" and ($insts[1] == "left" or $insts[1] == "right")) {
                                        if ($insts[1] == "right") {
                                            for($i=0;$i<(int)$insts[2];$i++) {
                                                array_unshift($pwd, array_pop($pwd));
                                            }
                                        } else if ($insts[1] == "left") {
                                            for($i=0;$i<(int)$insts[2];$i++) {
                                                array_push($pwd, array_shift($pwd));
                                            }
                                        }
                                    } else if ($insts[0] == "rotate" and $insts[1] == "based") {
                                        $key = array_search($insts[6], $pwd);
                                        $additional = ($key>3?2:1);
                                        for($i=0;$i<($key+$additional);$i++) {
                                            array_unshift($pwd, array_pop($pwd));
                                        }
                                    } else if ($insts[0] == "move") {
                                        $move = array($pwd[$insts[2]]);
                                        unset($pwd[$insts[2]]);
                                        $pwd = array_values($pwd);
                                        array_splice($pwd, (int)$insts[5], 0, $move);
                                    } else {
                                        continue;
                                    }
                                }

                                if (implode("", $pwd) == "fbgdceah") {
                                    echo "result: $a$b$c$d$e$f$g$h\n";
                                    exit;
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

?>
