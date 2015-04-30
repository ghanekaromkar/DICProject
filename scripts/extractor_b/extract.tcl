#!/usr/bin/tclsh

set dir [lindex $argv 0]
file mkdir $dir

set file_file [open [lindex $argv 1] "r"]
set files [split [read $file_file] "\n"]

foreach file $files {
	if {[file exists $file]} {
		puts "Processing $file now"
		set fin [open $file "r"]
		set file_data [read $fin]
		set data [split $file_data "\n"]
		set flag 0

		foreach line $data {
			if {[regexp "</doc>" $line]} {
				set flag 0
				close $fp
				continue
			} elseif { $flag } {
				puts $fp $line
				continue
			} elseif {[regexp "<doc.*title=\"(.*)\".*>" $line - a]} {
				regsub {[^a-zA-Z\d\s:]} $a "" a
				puts "Found new file $a"
				set flag 1
				set fp [open "$dir/$a.txt" "w"]
				continue
			}
		}

		close $fin
	}
}

close $file_file

