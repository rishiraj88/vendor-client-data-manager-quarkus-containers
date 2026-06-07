FROM postgres

ENV POSTGRES_DB decom
ENV POSTGRES_PASSWORD postgres

COPY 1-schema.sql /docker-entrypoint-initdb.d/
COPY 2-data.sql /docker-entrypoint-initdb.d/

EXPOSE 5432