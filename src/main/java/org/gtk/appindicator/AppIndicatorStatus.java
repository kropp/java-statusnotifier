package org.gtk.appindicator;

/**
 * Describes the status of this item or of the associated application.
 */
public enum AppIndicatorStatus {
  /**
   * The item doesn't convey important information to the user,
   * it can be considered an "idle" status and is likely that visualizations will chose to hide it.
   */
  PASSIVE,
  /**
   * The item is active, is more important that the item will be shown in some way to the user.
   */
  ACTIVE,
  /**
   * The item carries really important information for the user, such as battery charge running out and
   * wants to incentive the direct user intervention.
   * Visualizations should emphasize in some way the items with NeedsAttention status.
   */
  ATTENTION
}
