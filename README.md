# 🏎️ BoxBox F1 Companion App (Job Assignment Project)

**A beautifully designed Formula 1 companion app built using Jetpack Compose (Kotlin) and modern Android architecture.**  
This project demonstrates clean UI/UX, structured code organization, real-time updates, and smooth Compose animations — all inspired by a Figma design system.

---

## 🚀 Overview

The **BoxBox F1 App** provides an interactive way to explore Formula 1 race information, track stats, top drivers, and circuit details — all presented with a sleek, modern interface.

The app was designed and implemented as part of a **mobile developer job assignment** to showcase advanced Compose skills, UI fidelity to Figma, and efficient Kotlin development practices.

---

## 🎨 Design & Assets

- ✏️ **Figma-based UI**: All layouts faithfully match the provided Figma design, including typography, spacing, and color palette.  
- 🧩 **Vector Icons (SVG)**: The majority of icons (home, globe, trophy, calendar, etc.) are implemented as **SVG drawables** for crisp rendering and scalability across all screen densities.
- 🌈 **Custom Gradients & Typography**: Consistent use of Space Grotesk and Montserrat fonts, with attention to typographic hierarchy and spacing.
- 🧠 **Dynamic States**: Subtle color transitions, live countdowns, and visual cues to replicate a high-end F1-inspired interface.

---

## 🧱 Tech Stack

| Layer | Technology |
|-------|-------------|
| **Language** | Kotlin |
| **UI Toolkit** | Jetpack Compose |
| **Architecture** | MVVM (ViewModel + StateFlow) |
| **Dependency Injection** | Koin |
| **Navigation** | Jetpack Navigation Compose |
| **Design Source** | Figma |
| **Image Assets** | SVGs & PNGs (optimized for Compose) |

---

## 🧩 Key Features

### 🏁 Home Screen
- Auto-scrolling horizontal pager for **Top Driver** and **Community Card**
- Real-time **Race Session Countdown**
- “Follow Us” CTA with vibrant button and icon integration
- **Instagram Card** linking directly to BoxBox Club’s Instagram page
- Fixed **Bottom Navigation Bar** with smooth highlight transitions and gesture-safe positioning

### 🕹️ UI Components
- `TopDriverCard`: Gradient typography with live point updates  
- `StatCard`: Animated red progress-bar background with adaptive text  
- `RaceDetailScreen`: 3D circuit render background and live FP1 timer  
- `BottomNavBar`: Custom black bar with active icon feedback and shadow  
- `InstagramCard`: Interactive external link preview with embedded icon  

### ⚙️ Utility & Architecture
- Real-time epoch-based time utilities (`formatTimeLocal`, `formatDateLocal`)  
- Reusable Composables with consistent padding, font scaling, and accessibility support  
- Clean separation of **UI**, **Data**, and **Domain** layers

---

## 🧠 What This Project Demonstrates

✅ Expertise in **Jetpack Compose** and **Kotlin best practices**  
✅ Ability to **translate Figma designs** into pixel-perfect code  
✅ Understanding of **state management**, animations, and Compose layouts  
✅ Use of **SVGs** and scalable vector assets for modern UI design  
✅ Code clarity, reusability, and professional structure for production apps  

---

## 🧭 Project Structure

<img width="1080" height="2340" alt="Screenshot_20251106_215443" src="https://github.com/user-attachments/assets/5dbf9f12-6d97-4d4d-9691-6897a55d617f" />
<img width="1080" height="2340" alt="Screenshot_20251106_210622" src="https://github.com/user-attachments/assets/63e71b5e-2c29-45fd-bcae-03f78d40941f" />
<img width="1080" height="2340" alt="Screenshot_20251106_210559" src="https://github.com/user-attachments/assets/d8b1faf9-10bc-4896-9e80-1cb641f638dd" />
<img width="1080" height="2340" alt="Screenshot_20251106_210538" src="https://github.com/user-attachments/assets/ddc0caee-16d3-4fe9-8308-3837c04296fb" />
