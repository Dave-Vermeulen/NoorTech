# NoorTech Website Project Document & Design Specification

**Project Title:** NoorTech.app - Corporate Website & Digital Brand Identity  
**Version:** 1.0  
**Date:** August 31, 2025  
**Prepared For:** NoorTech Development Team

---

## 1.0 Brand Identity & Core Concepts

The design philosophy for the NoorTech website is professional minimalism with a focus on enlightenment and strategic guidance, as embodied by the brand name "Noor" (light) and the central motif of the "Black Queen" chess piece.

**Slogan:** "Illuminating business through tech."

**Visual Metaphor:** The Black Queen chess piece symbolizes strategic mastery and foresight. The golden light it casts represents the guiding, innovative, and ethical principles of NoorTech. This combination is the cornerstone of the visual language.

---

## 2.0 Color Palette

The color palette is meticulously crafted to be professional and high-contrast, ensuring legibility and a sophisticated user experience. All colors are provided with their hexadecimal codes.

| Name | HEX Code | Usage |
|------|----------|--------|
| Primary Dark | #0a0a0a | Main background color for all dark-themed sections |
| Secondary Dark | #1a1a1a | Used for containers, cards, and subtle background variations |
| Primary Accent | #FFC107 | The main "Noor" color. Used for all primary calls-to-action, icons, and key visual highlights |
| Secondary Accent | #FFD54F | Lighter shade of the primary accent, used for subtle glows, hover states, and lighter text on dark backgrounds |
| Primary Text | #f0f0f0 | The main text color for headings and body copy on dark backgrounds |
| Subtle Text | #a0a0a0 | Used for sub-headings, captions, and fine print |
| White | #FFFFFF | Used sparingly for high-contrast elements and certain icons on very dark backgrounds |

---

## 3.0 Typography

Two complementary font families are recommended to establish a clean and professional typographic hierarchy. Both are widely available and perform well on the web.

### Headings (H1, H2, H3)
**Font:** Poppins or a similar geometric sans-serif font  
**Reasoning:** Poppins has a modern, clean, and confident feel that is highly readable at large sizes.

### Body Text (P)
**Font:** Montserrat or a similar humanist sans-serif font  
**Reasoning:** Montserrat is well-balanced and provides excellent legibility for long-form content, ensuring a comfortable reading experience.

---

## 4.0 Core UI Components & Elements

This section details reusable components and their styling for consistent implementation across the site.

### Primary Button
- **Background:** #FFC107 (Primary Accent)
- **Text Color:** #0a0a0a (Primary Dark)
- **Border Radius:** 5px
- **Hover State:** Background becomes #FFD54F (Secondary Accent)

### Secondary Button
- **Background:** Transparent
- **Border:** 1px solid #FFC107 (Primary Accent)
- **Text Color:** #f0f0f0 (Primary Text)
- **Hover State:** Background becomes #FFC107 (Primary Accent)

### Input Fields
- **Background:** rgba(26, 26, 26, 0.5)
- **Border:** 1px solid #a0a0a0 (Subtle Text)
- **Focus State:** Border color becomes #FFC107 (Primary Accent) with a subtle glow

### Iconography
All icons are a single-line, minimalist style. They should be rendered in #FFC107 (Primary Accent) or #f0f0f0 (Primary Text) depending on the context.

**Service Icons Include:**
- WhatsApp logo
- Document/checkmark
- Cloud
- Dev team nodes
- Magnifying glass

### Luminous Effect
A subtle, diffuse glow effect using rgba(255, 193, 7, 0.3) as a transparent layer, applied to key elements to create a sense of light.

---

## 5.0 Page-by-Page Breakdown & Wireframe Specification

Each section is designed to be a distinct, full-width block that can be stacked or rearranged as needed.

### 5.1 Homepage (/)

#### Section 1: Hero
**Purpose:** The first impression. Communicates the brand and its value proposition.

**Elements:**
- NoorTech Logo in top-left
- H1: "Illuminating business through tech."
- Sub-heading text
- Primary Button (e.g., "Contact Us")

#### Section 2: Services Overview
**Purpose:** A quick glance at the company's main offerings.

**Elements:**
- A grid of four cards
- Each card contains:
  - Service Icon
  - Heading (WhatsApp for Business, Client Onboarding, Cloud Architecture, Healthcare Solutions)
  - Short description

#### Section 3: Our Commitment
**Purpose:** To build trust and showcase the company's values.

**Elements:**
- H2: "Our Commitment to Integrity"
- Paragraph of text
- Decorative Luminous Ray graphic

#### Section 4: Contact & Map
**Purpose:** To facilitate client contact.

**Elements:**
- Split layout
  - **Left side:** Form with Name, Email, and Message input fields
  - **Right side:** Stylized, abstract, and luminous map of Cape Town

### 5.2 Dedicated Product/Service Pages

Each product/service page follows a similar pattern for consistency, with a visual demonstration and a detailed feature list.

#### Section 1: Hero & Product Visual
**Purpose:** To introduce the specific product.

**Elements:**
- Large H1 heading (e.g., "WhatsApp for Business - Your Store, Right in the Chat")
- Compelling product mockup
- For the WhatsApp page: smartphone graphic displaying the product's UI

#### Section 2: Key Features
**Purpose:** To list and describe the product's core benefits.

**Elements:**
- List of bullet points with dedicated Icons for each feature
- Examples: Chatbot, Shopping Cart, Secure Payments

#### Section 3: Case Study (for Client Onboarding)
**Purpose:** To provide social proof and demonstrate expertise.

**Elements:**
- Heading: "Client Success Story"
- Short summary of the legal firm project
- Stylized graphic illustrating the streamlined workflow

#### Section 4: Dev Teams for Hire
**Purpose:** To explain the model and its benefits.

**Elements:**
- Dynamic visual of interconnected, glowing nodes to represent horizontal scaling
- Headings above each node for:
  - Product Testing (QA/QC)
  - Product Support
  - FinOps

---

## 6.0 Footer

The footer is designed to be lean and professional, reflecting the brand's efficiency.

**Elements:**
- NoorTech Logo & Slogan
- Quick links: Home, Services, About, Contact, Careers
- Social media icons (e.g., LinkedIn, Twitter)
- Copyright information

---

## Development Notes

This document serves as the foundation for the front-end build. The mockups and specifications provided here should guide the development process to ensure a cohesive and professional final product.

### Technical Requirements
- Responsive design for mobile, tablet, and desktop
- Fast loading times and optimized assets
- Accessibility compliance (WCAG 2.1 AA)
- SEO optimization
- Cross-browser compatibility

### Asset Requirements
- High-resolution Black Queen chess piece imagery
- Service icons in SVG format
- Professional photography or illustrations for case studies
- Optimized images for web delivery