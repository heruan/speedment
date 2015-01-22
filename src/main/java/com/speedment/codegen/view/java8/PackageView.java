/**
 *
 * Copyright (c) 2006-2015, Speedment, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); You may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.speedment.codegen.view.java8;

import com.speedment.codegen.CodeGenerator;
import com.speedment.codegen.model.package_.Package_;
import com.speedment.codegen.view.CodeView;
import java.util.Stack;
import java.util.stream.Collectors;
import static com.speedment.codegen.CodeUtil.*;

/**
 *
 * @author Duncan
 */
public class PackageView extends CodeView<Package_> {
	private final static String PACKAGE_STRING = "package ";
	
	@Override
	public CharSequence render(CodeGenerator renderer, Package_ model) {
		final Stack<CharSequence> packages = new Stack<>();

		Package_ p = model;
		do { packages.add(p.getName_()); } 
		while ((p = p.getPackage()) != null);
		
		return packages.stream().collect(Collectors.joining(DOT, PACKAGE_STRING, SC));
	}
}