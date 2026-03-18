# Criação de Banco de Dados Local

```sql

-- 1. Com usuário dba: postgres

create user usrexemplo with password 'senha';
create user usrexemplosvc with password 'senha';
create database exemplo owner usrexemplo;

-- 2. Com usuario do banco: (manualmente no psql) \c exemplo

revoke all on schema public from public;

grant create on schema public to usrexemplo;

grant usage on schema public to usrexemplosvc;
grant select, insert, update, delete on all tables in schema public to usrexemplosvc;
grant usage, select on all sequences in schema public to usrexemplosvc;

alter default privileges for role usrexemplo in schema public
  grant select, insert, update, delete
  on tables to usrexemplosvc;
alter default privileges for role usrexemplo in schema public
  grant usage, select
  on sequences to usrexemplosvc;

-- 3. (Opcional) Teste rápido (logado como usrexemplo)

create table teste (
  id serial primary key,
  nome varchar(50) not null
);

-- Agora usrexemplosvc consegue:
-- select, insert, update, delete
-- mas NÃO consegue criar tabelas
```

## CLI de Conexãom com o banco

`podman exec -it postgresql psql -U postgres`