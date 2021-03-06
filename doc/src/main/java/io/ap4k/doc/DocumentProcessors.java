/**
 * Copyright 2018 The original authors.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
**/

package io.ap4k.doc;

import io.sundr.transform.annotations.VelocityTransformation;
import io.sundr.transform.annotations.VelocityTransformations;
import io.sundr.codegen.annotations.PackageSelector;


@VelocityTransformations(value = @VelocityTransformation(value="/processor-doc.vm", outputPath="processors-table.org", gather=true),
                         packages = {
                           @PackageSelector(value="io.ap4k.processor"),
                           @PackageSelector(value="io.ap4k.docker.processor"),
                           @PackageSelector(value="io.ap4k.kubernetes.processor"),
                           @PackageSelector(value="io.ap4k.openshift.processor"),
                           @PackageSelector(value="io.ap4k.isito.processor"),
                           @PackageSelector(value="io.ap4k.servicecatalog.processor"),
                           @PackageSelector(value="io.ap4k.component.processor"),
                           @PackageSelector(value="io.ap4k.spring.processor"),
                           @PackageSelector(value="io.ap4k.thorntail"),
                           @PackageSelector(value="io.ap4k.micronaut")
                         })
public class DocumentProcessors {

}
