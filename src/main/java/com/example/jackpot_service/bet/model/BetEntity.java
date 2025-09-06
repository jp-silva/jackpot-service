package com.example.jackpot_service.bet.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

/**
 * JPA Entity for storing bet information in the database.
 */
@Entity
@Table(name = "bets")
public class BetEntity {

    @Id
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "jackpot_id", nullable = false)
    private UUID jackpotId;

    @Column(nullable = false)
    private int amount;

    /**
     * Default constructor for JPA.
     */
    public BetEntity() {
    }

    /**
     * Constructs a new BetEntity.
     *
     * @param id The unique identifier of the bet.
     * @param userId The unique identifier of the user who placed the bet.
     * @param jackpotId The unique identifier of the jackpot the bet is placed on.
     * @param amount The amount of the bet in the currency's smallest unit.
     */
    public BetEntity(UUID id, UUID userId, UUID jackpotId, int amount) {
        this.id = id;
        this.userId = userId;
        this.jackpotId = jackpotId;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getJackpotId() {
        return jackpotId;
    }

    public void setJackpotId(UUID jackpotId) {
        this.jackpotId = jackpotId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "BetEntity{" +
               "id=" + id +
               ", userId=" + userId +
               ", jackpotId=" + jackpotId +
               ", amount=" + amount +
               '}';
    }
}
