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
package io.ap4k.hook;

import io.ap4k.project.Project;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public abstract class ProjectHook implements Runnable {

  protected final Project project;

  public ProjectHook(Project project) {
    this.project = project;
  }

  /**
   * Things to execute when initializing.
   */
  public abstract void init();


  /**
   * Call an operation that will preload all required classes.
   * A shutdown hook cannot load new classes (at least it seems so).
   * So we need to preload all classes beforehand.
   */
  public abstract void warmup();

  /**
   * Register the hook for execution on shutdown.
   */
  public void register () {
    init();
    warmup();
    Runtime.getRuntime().addShutdownHook(new Thread(this));
  }

  public boolean exec(String... commands) {
    Process process = null;
    try {
      process = new ProcessBuilder()
        .directory(project.getRoot().toFile())
        .command(commands)
        .redirectErrorStream(true)
        .start();

      try (InputStreamReader isr = new InputStreamReader(process.getInputStream());
           BufferedReader reader = new BufferedReader(isr)) {

        for (String line = reader.readLine(); line != null; line = reader.readLine()) {
          System.out.println(line);
        }
        process.waitFor();
      }
    } catch (IOException e) {
      return false;
    } catch (InterruptedException e) {
      return false;
    } finally {
      if (process != null)  {
        return process.exitValue() == 0;
      } else {
        return false;
      }
    }
  }
}
