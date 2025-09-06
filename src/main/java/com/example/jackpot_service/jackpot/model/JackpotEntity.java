package com.example.jackpot_service.jackpot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

/**
 * Represents a Jackpot entity in the system.
 */
@Entity
@Table(name = "jackpots")
public class JackpotEntity {

    @Id
    private UUID id;

    private String contributionConfig;
    private String rewardsConfig;

    /**
     * Default constructor for JPA.
     */
    public JackpotEntity() {
    }

    /**
     * Constructs a new Jackpot with the specified details.
     *
     * @param id The unique identifier of the jackpot.
     * @param contributionConfig The configuration string for contribution calculation.
     * @param rewardsConfig The configuration string for rewards distribution.
     */
    public JackpotEntity(UUID id, String contributionConfig, String rewardsConfig) {
        this.id = id;
        this.contributionConfig = contributionConfig;
        this.rewardsConfig = rewardsConfig;
    }

    /**
     * Returns the ID of the jackpot.
     *
     * @return The jackpot ID.
     */
    public UUID getId() {
        return id;
    }

    /**
     * Sets the ID of the jackpot.
     *
     * @param id The jackpot ID to set.
     */
    public void setId(UUID id) {
        this.id = id;
    }

    /**
     * Returns the contribution configuration string for the jackpot.
     *
     * @return The contribution configuration.
     */
    public String getContributionConfig() {
        return contributionConfig;
    }

    /**
     * Sets the contribution configuration string for the jackpot.
     *
     * @param contributionConfig The contribution configuration to set.
     */
    public void setContributionConfig(String contributionConfig) {
        this.contributionConfig = contributionConfig;
    }

    /**
     * Returns the rewards configuration string for the jackpot.
     *
     * @return The rewards configuration.
     */
    public String getRewardsConfig() {
        return rewardsConfig;
    }

    /**
     * Sets the rewards configuration string for the jackpot.
     *
     * @param rewardsConfig The rewards configuration to set.
     */
    public void setRewardsConfig(String rewardsConfig) {
        this.rewardsConfig = rewardsConfig;
    }
}
