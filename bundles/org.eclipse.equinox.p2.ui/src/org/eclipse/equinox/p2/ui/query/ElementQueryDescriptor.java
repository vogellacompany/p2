/*******************************************************************************
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.equinox.p2.ui.query;

import org.eclipse.equinox.p2.query.*;

/**
 * Data class representing everything needed to run a query, including
 * the object to be queried, the query to use, and the query result.
 * 
 * @since 3.4
 */
public class ElementQueryDescriptor {

	public Query query;
	public Collector result;
	public IQueryable queryable;

	public ElementQueryDescriptor(IQueryable queryable, Query query, Collector result) {
		this.query = query;
		this.result = result;
		this.queryable = queryable;
	}
}
