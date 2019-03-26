/*******************************************************************************
 * Copyright (c) 2014-2019 Martin Weber.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *      Martin Weber - Initial implementation
 *******************************************************************************/
package de.marw.cdt.cmake.core.ui;

import org.eclipse.cdt.core.settings.model.ICResourceDescription;
import org.eclipse.cdt.ui.newui.AbstractCPropertyTab;
import org.eclipse.cdt.ui.newui.ICPropertyTab;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.PlatformUI;

/**
 * Common workarounds of quirks in AbstractCPropertyTab.
 *
 * @author Martin Weber
 */
public abstract class QuirklessAbstractCPropertyTab extends AbstractCPropertyTab {

  private String helpContextId;

  /**
   * Overridden because the super class always prefixes the ID with its own
   * bundle name.
   */
  @Override
  public final String getHelpContextId() {
    return helpContextId;
  }

  /**
   * Overridden because the super class always prefixes the ID with its own
   * bundle name.
   *
   * @param id
   *          the help context id, including the plugin id.
   */
  @Override
  public final void setHelpContextId(String id) {
    helpContextId = id;
  }

  @Override
  protected void createControls(Composite parent) {
    super.createControls(parent);
    PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, getHelpContextId());
  }

  /**
   * Overridden to have a logic here that <em>I</em> can understand.
   */
  @Override
  public void handleTabEvent(int kind, Object data) {
    switch (kind) {
    case ICPropertyTab.DEFAULTS:
      if (canBeVisible()) {
        performDefaults();
      }
      break;
    default:
      super.handleTabEvent(kind, data);
      break;
    }
  }

  /**
   * Makes the UI display the specified new settings.<br>
   * Overridden to have documentation. This documentation is reversed engineered from existing CDT code;
   * AbstractCPropertyTab.java lacks documentation.
   *
   * @param resd
   *          the setting to display
   */
  @Override
  protected abstract void updateData(ICResourceDescription resd);

  @Override
  protected void updateButtons() {
    // never called from superclass, but abstract :-)
  }
}