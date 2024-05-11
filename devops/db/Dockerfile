FROM postgres:latest
LABEL authors="Ugo Giordano"
MAINTAINER Ugo Giordano <ugo.amati@softwarecare.it>

ARG DB_DUMP_FILENAME

ADD ./data/$DB_DUMP_FILENAME /docker-entrypoint-initdb.d