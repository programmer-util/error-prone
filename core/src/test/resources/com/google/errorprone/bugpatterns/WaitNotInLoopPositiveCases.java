/*
 * Copyright 2013 Google Inc. All Rights Reserved.
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
 */

package com.google.errorprone.bugpatterns;

/**
 * @author eaftan@google.com (Eddie Aftandilian)
 */
public class WaitNotInLoopPositiveCases {
  
  boolean flag = true;
  
  public void test1() {
    synchronized (this) {
      if (!flag) {
        try {
          // BUG: Diagnostic contains: 
          wait();
        } catch (InterruptedException e) {
        }
      }
    }
  }
  
  public void test2() {
    synchronized (this) {
      if (!flag) {
        try {
          // BUG: Diagnostic contains: 
          wait(1000);
        } catch (InterruptedException e) {
        }
      }
    }
  }
  
  public void test3() {
    synchronized (this) {
      if (!flag) {
        try {
          // BUG: Diagnostic contains: 
          wait(1000, 1000);
        } catch (InterruptedException e) {
        }
      }
    }
  }
  
  public void testLoopNotInSynchronized() {
    while (!flag) {
      synchronized (this) {
        System.out.println("foo");
        try {
          // BUG: Diagnostic contains: 
          wait(1000, 1000);
        } catch (InterruptedException e) {
        }
      }
    }
  }

}
