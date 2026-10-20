# TWDP OpenGL Labs

Computer graphics laboratory assignments built with Java and JOGL (OpenGL 2.x).

---

## Lab 1: Lighting, Transformations & Heightmap Terrain

Implementation of a basic 3D interactive scene featuring custom geometry, lighting controls, and displacement-based terrain modeling.

### Features
* **Circling Light Source:** Orbiting light (`GL_LIGHT0`) with dynamic position recalculation via trigonometric functions. Can be toggled on/off.
* **Custom Model Geometry:** Added chimney geometry using quad primitives (`GL_QUADS`) with proper surface normals to match the scene's diffuse lighting model.
* **Model Transformations:** Global matrix translation and localized object rotation using `glRotatef` around the Y-axis.
* **Interactive Lighting:** Dynamic diffuse/specular color switching between red, green, and blue presets, with a fallback to the default white light.
* **Heightmap Terrain (`GroundRetro`):** Grid-based terrain generated via sampling grayscale values from `DisplacementMap.png`, applying variable vertex heights ($Y$) across a subdivided quad plane.

---

## Lab 2: Particle System (Chimney Smoke)

Implementation of a billboarded particle system simulating chimney smoke with physical behavior, environmental wind forces, and frame-rate independent updates.

### Features
* **Smoke Emitter & Billboarding:** Integrated `SmokeEmitter` mapped directly to the chimney coordinates, ensuring camera-facing textured particle quads.
* **Lifecycle & Dissipation:** Particle transparency modulated by remaining energy (`alpha = energy * 0.4`), random jitter for turbulence, and vertical cone-shaped expansion.
* **Weather & Wind Forces:** Real-time smoke deflection reacting dynamically to 3D directional wind vectors supplied by the `Weather` class.
* **Dynamic Particle Count:** Real-time particle density scaling (spawning and despawning particle batches) via keyboard controls.
* **Frame-Rate Independence (Delta Time):** Physics and fading speeds decoupled from rendering frame rate using `Global.timeScaler` calculated via `System.nanoTime()`.

---

### Screenshot
<img width="782" height="588" alt="image" src="https://github.com/user-attachments/assets/da1bfce1-45a2-4659-8e60-cfad363e9af7" />


---

### Controls

| Key | Action |
| :--- | :--- |
| **W, S, A, D** | Move camera forward / backward / left / right |
| **Q, E** | Move camera down / up |
| **Arrow Keys** | Rotate camera (pitch & yaw) |
| **L** | Toggle orbiting light movement |
| **1, 2, 3** | Switch light color to Red / Green / Blue |
| **0** | Reset light color to Default (White) |
| **Numpad 8 / 2** | Move light along Z-axis |
| **Numpad 4 / 6** | Move light along X-axis |
| **Page Up / Page Down** | Move light along Y-axis |
| **Z, X** | Adjust wind along X-axis (left / right) |
| **C, V** | Adjust wind along Z-axis (forward / backward) |
| **B, N** | Adjust wind along Y-axis (up / down) |
| **K / J** | Add / remove smoke particles |
| **F1** | Toggle FPS counter |
| **R** | Toggle Points / Wireframe / Solid render mode |
| **P** | Toggle post-processing shaders |
| **ESC** | Exit application |

---

### Tech Stack & Prerequisites
* **Java 17** (or compatible JDK)
* **JOGL 2.3.2** (with matching native libraries for `gluegen-rt` and `jogl-all`)
* **IntelliJ IDEA** / NetBeans / Eclipse

### How to Run
1. Open the project in IntelliJ IDEA.
2. Ensure JOGL jars (`gluegen-rt.jar`, `jogl-all.jar`) and the native binaries folder (`natives-amd64`) are correctly referenced in the project dependencies.
3. Run `src/ksg/WorldMain.java`.
