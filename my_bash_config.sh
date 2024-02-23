#!/bin/bash

# author: Elliot Holmes

# run from the base 
# directory of the 
# project by typing
# ". my_bash_config.sh"
# do this every time  
# you open a new terminal

# after running once in 
# the base directory you
# can run from any dir
# by running "config_bash"
# unless you open a new 
# terminal in which case 
# you would need to run 
# ". my_bash_config.sh"
# from the base directory again

# if you "unalias -a" you 
# will need to run 
# ". $my_bash_config"
# in order to get the 
# "config_bash" alias back
# along with all other
# aliases

# "$base_dir" will be replaced
# by the absolute path
# to the home directory

# if you accidentally run
# this program from
# somewhere other than 
# the base directory of 
# your project you can
# open a new terminal
# and run this file in the base
# directory or you could
# run "unset _already_run"
# then run this file in 
# the base directory

# you may want to ignore 
# most of this file 
# and simply adjust the
# lines beneath the # aliases
# and # classpath environment var
# but you also may benefit
# from adjusting the behavior
# of this program to your
# liking

# success does not 
# necessarily mean that
# no errors occured, but
# rather that nothing
# stopped the execution
# of the file

unalias -a

# any code you only wish to execute
# the first time should be placed
# within this if statement
if [ "$_already_run" != 1 ]; then 
    export base_dir=`pwd`
    echo now defined: config_bash, base_dir, my_bash_config
fi
export _already_run=1
alias config_bash=". $base_dir/my_bash_config.sh"
export my_bash_config="$base_dir/my_bash_config.sh"


# classpath environment var
export CLASSPATH=".:$base_dir/stdlib:$base_dir/MyLibs"

# aliases
alias jdb="jdb -sourcepath $CLASSPATH"


echo success
