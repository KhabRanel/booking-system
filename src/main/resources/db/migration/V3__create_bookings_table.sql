CREATE TABLE bookings (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id     UUID         NOT NULL REFERENCES users(id),
    resource_id UUID         NOT NULL REFERENCES resources(id),
    start_time  TIMESTAMP WITH TIME ZONE    NOT NULL,
    end_time    TIMESTAMP WITH TIME ZONE    NOT NULL,
    status      VARCHAR(50)  NOT NULL DEFAULT 'PENDING',
    created_at  TIMESTAMP WITH TIME ZONE    NOT NULL DEFAULT now(),
    version     BIGINT       NOT NULL DEFAULT 0,

    CONSTRAINT chk_time CHECK (end_time > start_time)
);

CREATE INDEX idx_bookings_resource_time
    ON bookings(resource_id, start_time, end_time);

CREATE UNIQUE INDEX idx_no_double_booking
    ON bookings(resource_id, start_time, end_time)
    WHERE status NOT IN ('CANCELLED', 'EXPIRED');