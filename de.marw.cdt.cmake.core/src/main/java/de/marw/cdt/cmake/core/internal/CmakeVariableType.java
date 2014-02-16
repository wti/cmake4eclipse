/*******************************************************************************
 * Copyright (c) 2014 Martin Weber.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *      Martin Weber - Initial implementation
 *******************************************************************************/
package de.marw.cdt.cmake.core.internal;

/**
 * The type identifier of a cmake variable.
 *
 * @author Martin Weber
 */
public enum CmakeVariableType {
  /** Boolean ON/OFF checkbox */
  BOOL {
    @Override
    String getCmakeArg() {
      return "BOOL";
    }
  },
  /** File chooser dialog */
  FILEPATH {
    @Override
    String getCmakeArg() {
      return "FILEPATH";
    }
  },
  /** Directory chooser dialog */
  PATH {
    @Override
    String getCmakeArg() {
      return "PATH";
    }
  },
  /** Arbitrary string */
  STRING {
    @Override
    String getCmakeArg() {
      return "STRING";
    }
  },
  /** No GUI entry (used for persistent variables) */
  INTERNAL {
    @Override
    String getCmakeArg() {
      return "INTERNAL";
    }
  };

  /**
   * Gets the type as a valid commandline argument for cmake.
   */
  abstract String getCmakeArg();
}