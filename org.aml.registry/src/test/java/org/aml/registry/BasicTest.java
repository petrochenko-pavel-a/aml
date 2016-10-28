package org.aml.registry;

import org.aml.registry.internal.CommitterInfo;
import org.aml.registry.model.Registry;
import org.aml.registry.operations.BuildStat;
import org.aml.registry.operations.LoadRegistry;
import org.aml.registry.operations.PublishRegistry;
import org.aml.registry.operations.RegistryMaterialize;
import org.aml.registry.operations.ResolveRegistry;
import org.eclipse.egit.github.core.RepositoryId;
import org.junit.Test;

public class BasicTest {

	LoadRegistry loadRegistry = new LoadRegistry("https://raw.githubusercontent.com/apiregistry/registry/master/registry.json");
	
	@Test
	public void test() {
		String user = System.getenv().get("GIT_USER");
		String password = System.getenv().get("GIT_PASSWORD");
		
		Registry apply = new ResolveRegistry().apply(
				loadRegistry.get());
		if (user != null && password != null) {
			new PublishRegistry(
					new CommitterInfo(user, password, "pavel@onpositive.com", "Petrochenko Pavel"),
					new RepositoryId("apiregistry", "registry")).accept(apply);
		}
		System.out.println(new BuildStat().apply(apply));
	}
	@Test
	public void test1(){
		new RegistryMaterialize("C:/work/registry").accept(new ResolveRegistry().apply(loadRegistry.get()));
	}

}
