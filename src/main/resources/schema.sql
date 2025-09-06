CREATE TABLE jackpots
(
    id                  UUID PRIMARY KEY,
    contribution_config VARCHAR(255) NOT NULL,
    rewards_config      VARCHAR(255) NOT NULL
);
INSERT INTO jackpots (id, contribution_config, rewards_config)
VALUES ('a277d806-1a18-4371-8fc2-442e713c140a', 'FIXED_PERCENTAGE', 'rewardA'),
       ('9cca1449-a55e-41b7-85f5-40321c24f413', 'VARYING_PERCENTAGE', 'rewardB');



CREATE TABLE bets
(
    id         UUID PRIMARY KEY,
    user_id    UUID NOT NULL,
    jackpot_id UUID NOT NULL,
    amount     INT  NOT NULL,
    FOREIGN KEY (jackpot_id) REFERENCES jackpots (id)
);

CREATE TABLE jackpot_contributions
(
    id                     UUID PRIMARY KEY,
    jackpot_id             UUID                                NOT NULL,
    bet_id                 UUID                                NOT NULL,
    user_id                UUID                                NOT NULL,
    contribution_amount    INT                                 NOT NULL,
    current_jackpot_amount INT                                 NOT NULL,
    created_at             TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    FOREIGN KEY (bet_id) REFERENCES bets (id),
    FOREIGN KEY (jackpot_id) REFERENCES jackpots (id)
);