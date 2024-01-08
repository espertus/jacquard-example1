#!/usr/bin/env bash

common/prepare_autograder.sh
common/run_autograder.py `basename "$PWD"`
