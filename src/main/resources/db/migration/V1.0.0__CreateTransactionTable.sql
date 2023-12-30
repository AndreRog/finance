CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- V1__Create_Transaction_Table.sql

-- Create the transaction table
CREATE TABLE transaction (
    id SERIAL PRIMARY KEY,
    uuid UUID DEFAULT uuid_generate_v4(),
    value NUMERIC NOT NULL,
    date TIMESTAMPTZ,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);