/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.jclouds.abiquo.features;

import org.jclouds.abiquo.domain.cloud.TemplateDefinition;
import org.jclouds.abiquo.domain.cloud.VirtualMachineTemplate;
import org.jclouds.abiquo.domain.cloud.options.ConversionOptions;
import org.jclouds.abiquo.domain.cloud.options.VirtualMachineTemplateOptions;
import org.jclouds.abiquo.domain.infrastructure.Datacenter;

import com.abiquo.model.transport.AcceptedRequestDto;
import com.abiquo.server.core.appslibrary.ConversionDto;
import com.abiquo.server.core.appslibrary.ConversionsDto;
import com.abiquo.server.core.appslibrary.VirtualMachineTemplateDto;
import com.abiquo.server.core.appslibrary.VirtualMachineTemplatePersistentDto;
import com.abiquo.server.core.appslibrary.VirtualMachineTemplateRequestDto;
import com.abiquo.server.core.appslibrary.VirtualMachineTemplatesDto;

/**
 * Provides synchronous access to Abiquo Apps library API.
 * 
 * @see API: <a href="http://community.abiquo.com/display/ABI20/API+Reference">
 *      http://community.abiquo.com/display/ABI20/API+Reference</a>
 * @see VirtualMachineTemplateAsyncApi
 * @author Ignasi Barrera
 * @author Francesc Montserrat
 */
public interface VirtualMachineTemplateApi {
   /*********************** Virtual Machine Template ***********************/

   /**
    * List all virtual machine templates for an enterprise in a datacenter
    * repository.
    * 
    * @param enterpriseId
    *           Id of the enterprise.
    * @param datacenterRepositoryId
    *           Id of the datacenter repository containing the templates.
    * @return The list of virtual machine templates for the enterprise in the
    *         datacenter repository.
    */
   VirtualMachineTemplatesDto listVirtualMachineTemplates(Integer enterpriseId, Integer datacenterRepositoryId);

   /**
    * List all virtual machine templates for an enterprise in a datacenter
    * repository.
    * 
    * @param enterpriseId
    *           Id of the enterprise.
    * @param datacenterRepositoryId
    *           Id of the datacenter repository containing the templates.
    * @param options
    *           The options to query the virtual machine templates.
    * @return The filtered list of virtual machine templates for the enterprise
    *         in the datacenter repository.
    */
   VirtualMachineTemplatesDto listVirtualMachineTemplates(Integer enterpriseId, Integer datacenterRepositoryId,
         VirtualMachineTemplateOptions options);

   /**
    * Get the given virtual machine template.
    * 
    * @param enterpriseId
    *           Id of the enterprise.
    * @param datacenterRepositoryId
    *           Id of the datacenter repository containing the templates.
    * @param enterpriseId
    *           The id of the virtual machine template.
    * @return The virtual machine template or <code>null</code> if it does not
    *         exist.
    */
   VirtualMachineTemplateDto getVirtualMachineTemplate(Integer enterpriseId, Integer datacenterRepositoryId,
         Integer virtualMachineTemplateId);

   /**
    * Updates an existing virtual machine template.
    * 
    * @param template
    *           The new attributes for the template.
    * @return The updated template.
    */
   VirtualMachineTemplateDto updateVirtualMachineTemplate(VirtualMachineTemplateDto template);

   /**
    * Deletes an existing virtual machine template.
    * 
    * @param template
    *           The virtual machine template to delete.
    */
   void deleteVirtualMachineTemplate(VirtualMachineTemplateDto template);

   /**
    * Creates a persistent virtual machine template from other virtual machine
    * template.
    * 
    * @param datacenterRepositoryId
    *           The repository where the persistent virtual machine template
    *           will be created.
    * @param options
    *           The persistent options like name, volume/tier, virtual
    *           datacenter and original template.
    * @return Traceable task response.
    */
   AcceptedRequestDto<String> createPersistentVirtualMachineTemplate(Integer enterpriseId,
         Integer datacenterRepositoryId, VirtualMachineTemplatePersistentDto persistentOptions);

   /**
    * Creates a new virtual machine template in the given datacenter.
    * <ul>
    * <li>It can download a template definition @see
    * {@link TemplateDefinition#downloadToRepository(Datacenter)}</li>
    * <li>Or promote a virtual machine template instance @see
    * {@link VirtualMachineTemplate#promoteToMaster(String)}</li>
    * </ul>
    * 
    * @param enterpriseId
    *           Id of the enterprise
    * @param datacenterRepositoryId
    *           The datacenter repository where the new virtual machine template
    *           will be created.
    * @param templateRequest
    *           <ul>
    *           <li>To downloading a template definition: Hold the template
    *           definition link with _rel_ ''templateDefinition''</li>
    *           <li>To promote a virtual machine template instance: Hold the
    *           virtual machine template link with _rel_
    *           ''virtualmachinetemplate'' and also the desired
    *           ''promotedName'', name of the new created virtual machine
    *           template</li>
    *           </ul>
    */
   AcceptedRequestDto<String> createVirtualMachineTemplate(Integer enterpriseId, Integer datacenterRepositoryId,
         VirtualMachineTemplateRequestDto templateRequest);

   /**
    * List all the conversions for a virtual machine template.
    * 
    * @param template
    *           , The virtual machine template of the conversions.
    * @return The list of conversions for the virtual machine template.
    */
   ConversionsDto listConversions(VirtualMachineTemplateDto template);

   /**
    * List conversions for a virtual machine template.
    * 
    * @param template
    *           , The virtual machine template of the conversions
    * @param options
    *           , Optionally filter compatible conversions with a provided
    *           hypervisor or with the desired state.
    * @return The list of conversions for the virtual machine template with the
    *         applied constrains.
    */
   ConversionsDto listConversions(VirtualMachineTemplateDto template, ConversionOptions options);

   /**
    * Get the conversions for a virtual machine template and the desired target
    * format.
    * 
    * @param template
    *           , The virtual machine template of the conversion
    * @param targetFormat
    *           The disk format type of the requested conversion
    * @return The conversions for the virtual machine template with the desired
    *         target disk format type.
    */
   ConversionDto getConversion(VirtualMachineTemplateDto template, String targetFormat);

   /**
    * Starts a V2V conversion of the current virtual machine template, or
    * updates a failed conversion.
    * 
    * @param template
    *           The virtual machine template to convert
    * @param targetFormat
    *           The requested target {@link DiskFormatType} of the conversion.
    * @param conversion
    *           , the dto representing the conversion
    * @return an accepted request with a link to track the progress of the
    *         conversion tasks.
    */
   AcceptedRequestDto<String> requestConversion(VirtualMachineTemplateDto template, String targetFormat,
         ConversionDto conversion);
}
