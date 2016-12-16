/*****************************************************************************
 * Copyright (c) 2016, 2017 CEA LIST and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Nicolas FAUVERGUE (ALL4TEC) nicolas.fauvergue@all4tec.net - Initial API and implementation
 *   Thanh Liem PHAN (ALL4TEC) thanhliem.phan@all4tec.net - Bug 417095
 *****************************************************************************/

package org.eclipse.papyrus.infra.nattable.style.configattribute;

import org.eclipse.nebula.widgets.nattable.export.ILayerExporter;
import org.eclipse.nebula.widgets.nattable.style.ConfigAttribute;
import org.eclipse.papyrus.infra.nattable.export.image.ImageFormat;

/**
 * Papyrus configuration attributes that are used to configure the export functionality.
 *
 * @since 2.0
 */
public interface PapyrusExportConfigAttributes {

	/**
	 * The configuration attribute for specifying the concrete implementation
	 * instance of ILayerExporter that should be used for a file export.
	 */
	ConfigAttribute<ILayerExporter> SIMPLE_FILE_EXPORTER = new ConfigAttribute<ILayerExporter>();

	/** The configuration attribute for the export image format. */
	ConfigAttribute<ImageFormat> EXPORT_IMAGE_FORMAT = new ConfigAttribute<ImageFormat>();

	/** The configuration attribute for the export image file name. */
	ConfigAttribute<String> EXPORT_IMAGE_FILENAME = new ConfigAttribute<String>();

	/** The configuration attribute to use the default Papyrus ExportTableDialog for exporting image. */
	ConfigAttribute<Boolean> EXPORT_IMAGE_USE_PAPYRUS_EXPORT_TABLE_DIALOG = new ConfigAttribute<Boolean>();
}
