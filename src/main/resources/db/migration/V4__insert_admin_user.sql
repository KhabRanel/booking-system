INSERT INTO users (id, email, password, full_name, role, created_at)
VALUES (
    gen_random_uuid(),
    'admin@booking.com',
    '$2a$10$slKSq4NdaIUJWlcAK7w.werA.cumwldG2KGKGGbzLVB4y3JbiqMeO',
    'System Admin',
    'ADMIN',
    now()
);