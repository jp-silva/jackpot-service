package com.example.jackpot_service.jackpot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a contribution made to a jackpot.
 * This entity maps to the 'jackpot_contributions' table in the database.
 */
@Entity
@Table(name = "jackpot_contributions")
public class JackpotContributionEntity {

    /**
     * The unique identifier for the jackpot contribution.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The ID of the jackpot to which the contribution was made.
     */
    @Column(name = "jackpot_id", nullable = false)
    private UUID jackpotId;

    /**
     * The ID of the bet that generated this contribution.
     */
    @Column(name = "bet_id", nullable = false)
    private UUID betId;

    /**
     * The ID of the user who made the bet that generated this contribution.
     */
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    /**
     * The amount of money contributed to the jackpot.
     */
    @Column(name = "contribution_amount", nullable = false)
    private Integer contributionAmount;

    /**
     * The current total amount of the jackpot after this contribution.
     */
    @Column(name = "current_jackpot_amount", nullable = false)
    private Integer currentJackpotAmount;

    /**
     * The timestamp when the contribution was created.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    /**
     * Default constructor.
     */
    public JackpotContributionEntity() {
    }

    /**
     * Constructs a new JackpotContributionEntity.
     *
     * @param jackpotId The ID of the jackpot.
     * @param betId The ID of the bet.
     * @param userId The ID of the user.
     * @param contributionAmount The amount contributed.
     * @param currentJackpotAmount The current total amount of the jackpot.
     * @param createdAt The creation timestamp.
     */
    public JackpotContributionEntity(UUID jackpotId, UUID betId, UUID userId, Integer contributionAmount, Integer currentJackpotAmount, LocalDateTime createdAt) {
        this.jackpotId = jackpotId;
        this.betId = betId;
        this.userId = userId;
        this.contributionAmount = contributionAmount;
        this.currentJackpotAmount = currentJackpotAmount;
        this.createdAt = createdAt;
    }

    /**
     * Returns the ID of the jackpot contribution.
     * @return The contribution ID.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the ID of the jackpot contribution.
     * @param id The contribution ID.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the ID of the jackpot.
     * @return The jackpot ID.
     */
    public UUID getJackpotId() {
        return jackpotId;
    }

    /**
     * Sets the ID of the jackpot.
     * @param jackpotId The jackpot ID.
     */
    public void setJackpotId(UUID jackpotId) {
        this.jackpotId = jackpotId;
    }

    /**
     * Returns the ID of the bet.
     * @return The bet ID.
     */
    public UUID getBetId() {
        return betId;
    }

    /**
     * Sets the ID of the bet.
     * @param betId The bet ID.
     */
    public void setBetId(UUID betId) {
        this.betId = betId;
    }

    /**
     * Returns the ID of the user.
     * @return The user ID.
     */
    public UUID getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user.
     * @param userId The user ID.
     */
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    /**
     * Returns the contribution amount.
     * @return The contribution amount.
     */
    public Integer getContributionAmount() {
        return contributionAmount;
    }

    /**
     * Sets the contribution amount.
     * @param contributionAmount The contribution amount.
     */
    public void setContributionAmount(Integer contributionAmount) {
        this.contributionAmount = contributionAmount;
    }

    /**
     * Returns the current jackpot amount.
     * @return The current jackpot amount.
     */
    public Integer getCurrentJackpotAmount() {
        return currentJackpotAmount;
    }

    /**
     * Sets the current jackpot amount.
     * @param currentJackpotAmount The current jackpot amount.
     */
    public void setCurrentJackpotAmount(Integer currentJackpotAmount) {
        this.currentJackpotAmount = currentJackpotAmount;
    }

    /**
     * Returns the creation timestamp.
     * @return The creation timestamp.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * Sets the creation timestamp.
     * @param createdAt The creation timestamp.
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
