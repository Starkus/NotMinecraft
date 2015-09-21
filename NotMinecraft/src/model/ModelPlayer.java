package model;

import engine.Polygon;
import engine.UVVertex;
import engine.Vertex;

public class ModelPlayer extends ModelBase{
	public ModelPlayer(String tex) {
		super(tex);
		
		getBounds();
		getVert();
		getUV();
		getMesh();
	}
	
	public void getBounds() {
		bounds = new float[] {1F/16*10, 1F/16*20, 1F/32*10};
	}
	public void getVert()
	{	

		v = new Vertex[48];
		// Head
		v[0] = new Vertex(1.2f, 0.0f, 2.4f);
		v[1] = new Vertex(0.4f, 0.0f, 2.4f);
		v[2] = new Vertex(0.4f, 0.8f, 2.4f);
		v[3] = new Vertex(1.2f, 0.8f, 2.4f);
		v[4] = new Vertex(1.2f, 0.0f, 3.2f);
		v[5] = new Vertex(0.4f, 0.0f, 3.2f);
		v[6] = new Vertex(0.4f, 0.8f, 3.2f);
		v[7] = new Vertex(1.2f, 0.8f, 3.2f);
		// Body
		v[8] = new Vertex(1.2f, 1.2f, 0.2f);
		v[9] = new Vertex(0.4f, 1.2f, 0.2f);
		v[10] = new Vertex(0.4f, 1.2f, 0.6f);
		v[11] = new Vertex(1.2f, 1.2f, 0.6f);
		v[12] = new Vertex(1.2f, 2.4f, 0.2f);
		v[13] = new Vertex(0.4f, 2.4f, 0.2f);
		v[14] = new Vertex(0.4f, 2.4f, 0.6f);
		v[15] = new Vertex(1.2f, 2.4f, 0.6f);
		// Left Arm
		v[16] = new Vertex(1.6f, 1.2f, 0.2f);
		v[17] = new Vertex(1.2f, 1.2f, 0.2f);
		v[18] = new Vertex(1.2f, 1.2f, 0.6f);
		v[19] = new Vertex(1.6f, 1.2f, 0.6f);
		v[20] = new Vertex(1.6f, 2.4f, 0.2f);
		v[21] = new Vertex(1.2f, 2.4f, 0.2f);
		v[22] = new Vertex(1.2f, 2.4f, 0.6f);
		v[23] = new Vertex(1.6f, 2.4f, 0.6f);
		// Right Arm
		v[24] = new Vertex(0.4f, 1.2f, 0.2f);
		v[25] = new Vertex(0.0f, 1.2f, 0.2f);
		v[26] = new Vertex(0.0f, 1.2f, 0.6f);
		v[27] = new Vertex(0.4f, 1.2f, 0.6f);
		v[28] = new Vertex(0.4f, 2.4f, 0.2f);
		v[29] = new Vertex(0.0f, 2.4f, 0.2f);
		v[30] = new Vertex(0.0f, 2.4f, 0.6f);
		v[31] = new Vertex(0.4f, 2.4f, 0.6f);
		// Left Leg
		v[32] = new Vertex(0.8f, 0.0f, 0.2f);
		v[33] = new Vertex(0.4f, 0.0f, 0.2f);
		v[34] = new Vertex(0.4f, 0.0f, 0.6f);
		v[35] = new Vertex(0.8f, 0.0f, 0.6f);
		v[36] = new Vertex(0.8f, 1.2f, 0.2f);
		v[37] = new Vertex(0.4f, 1.2f, 0.2f);
		v[38] = new Vertex(0.4f, 1.2f, 0.6f);
		v[39] = new Vertex(0.8f, 1.2f, 0.6f);
		// Right Leg
		v[40] = new Vertex(1.2f, 0.0f, 0.2f);
		v[41] = new Vertex(0.8f, 0.0f, 0.2f);
		v[42] = new Vertex(0.8f, 0.0f, 0.6f);
		v[43] = new Vertex(1.2f, 0.0f, 0.6f);
		v[44] = new Vertex(1.2f, 1.2f, 0.2f);
		v[45] = new Vertex(0.8f, 1.2f, 0.2f);
		v[46] = new Vertex(0.8f, 1.2f, 0.6f);
		v[47] = new Vertex(1.2f, 1.2f, 0.6f);
	}
	public void getUV()
	{
		uv = new UVVertex[96];
		
		// Head Front UV
		uv[0] = new UVVertex(1f/64*8, 1f/32*8);
		uv[1] = new UVVertex(1f/64*16, 1f/32*8);
		uv[2] = new UVVertex(1f/64*8, 1f/32*16);
		uv[3] = new UVVertex(1f/64*16, 1f/32*16);
		// Head Left UV
		uv[4] = new UVVertex(0f, 1f/32*8);
		uv[5] = new UVVertex(1f/64*8, 1f/32*8);
		uv[6] = new UVVertex(0f, 1f/32*16);
		uv[7] = new UVVertex(1f/64*8, 1f/32*16);
		// Head Right UV
		uv[8] = new UVVertex(1f/64*16, 1f/32*8);
		uv[9] = new UVVertex(1f/64*24, 1f/32*8);
		uv[10] = new UVVertex(1f/64*16, 1f/32*16);
		uv[11] = new UVVertex(1f/64*24, 1f/32*16);
		// Head Back UV
		uv[12] = new UVVertex(1f/64*24, 1f/32*8);
		uv[13] = new UVVertex(1f/64*32, 1f/32*8);
		uv[14] = new UVVertex(1f/64*24, 1f/32*16);
		uv[15] = new UVVertex(1f/64*32, 1f/32*16);
		// Head Top UV
		uv[16] = new UVVertex(1f/64*8, 0f);
		uv[17] = new UVVertex(1f/64*16, 0f);
		uv[18] = new UVVertex(1f/64*8, 1f/32*8);
		uv[19] = new UVVertex(1f/64*16, 1f/32*8);
		// Head Bottom UV
		uv[20] = new UVVertex(1f/64*16, 0f);
		uv[21] = new UVVertex(1f/64*24, 0f);
		uv[22] = new UVVertex(1f/64*16, 1f/32*8);
		uv[23] = new UVVertex(1f/64*24, 1f/32*8);
		
		// Body Front UV
		uv[24] = new UVVertex(1f/64*20, 1f/32*20);
		uv[25] = new UVVertex(1f/64*28, 1f/32*20);
		uv[26] = new UVVertex(1f/64*20, 1f/32*32);
		uv[27] = new UVVertex(1f/64*28, 1f/32*32);
		// Body Left UV
		uv[28] = new UVVertex(1f/64*16, 1f/32*20);
		uv[29] = new UVVertex(1f/64*20, 1f/32*20);
		uv[30] = new UVVertex(1f/64*16, 1f/32*32);
		uv[31] = new UVVertex(1f/64*20, 1f/32*32);
		// Body Right UV
		uv[32] = new UVVertex(1f/64*28, 1f/32*20);
		uv[33] = new UVVertex(1f/64*32, 1f/32*20);
		uv[34] = new UVVertex(1f/64*28, 1f/32*32);
		uv[35] = new UVVertex(1f/64*32, 1f/32*32);
		// Body Back UV
		uv[36] = new UVVertex(1f/64*32, 1f/32*20);
		uv[37] = new UVVertex(1f/64*40, 1f/32*20);
		uv[38] = new UVVertex(1f/64*32, 1f/32*32);
		uv[39] = new UVVertex(1f/64*40, 1f/32*32);
		// Body Top UV
		uv[40] = new UVVertex(1f/64*20, 1f/32*16);
		uv[41] = new UVVertex(1f/64*28, 1f/32*16);
		uv[42] = new UVVertex(1f/64*20, 1f/32*20);
		uv[43] = new UVVertex(1f/64*28, 1f/32*20);
		// Body Bottom UV
		uv[44] = new UVVertex(1f/64*28, 1f/32*16);
		uv[45] = new UVVertex(1f/64*36, 1f/32*16);
		uv[46] = new UVVertex(1f/64*28, 1f/32*20);
		uv[47] = new UVVertex(1f/64*36, 1f/32*20);
		
		// Arm Front UV
		uv[48] = new UVVertex(1f/64*44, 1f/32*20);
		uv[49] = new UVVertex(1f/64*48, 1f/32*20);
		uv[50] = new UVVertex(1f/64*44, 1f/32*32);
		uv[51] = new UVVertex(1f/64*48, 1f/32*32);
		// Arm Left UV
		uv[52] = new UVVertex(1f/64*40, 1f/32*20);
		uv[53] = new UVVertex(1f/64*44, 1f/32*20);
		uv[54] = new UVVertex(1f/64*40, 1f/32*32);
		uv[55] = new UVVertex(1f/64*44, 1f/32*32);
		// Arm Right UV
		uv[56] = new UVVertex(1f/64*48, 1f/32*20);
		uv[57] = new UVVertex(1f/64*52, 1f/32*20);
		uv[58] = new UVVertex(1f/64*48, 1f/32*32);
		uv[59] = new UVVertex(1f/64*52, 1f/32*32);
		// Arm Back UV
		uv[60] = new UVVertex(1f/64*52, 1f/32*20);
		uv[61] = new UVVertex(1f/64*56, 1f/32*20);
		uv[62] = new UVVertex(1f/64*52, 1f/32*32);
		uv[63] = new UVVertex(1f/64*56, 1f/32*32);
		// Arm Top UV
		uv[64] = new UVVertex(1f/64*44, 1f/32*16);
		uv[65] = new UVVertex(1f/64*48, 1f/32*16);
		uv[66] = new UVVertex(1f/64*44, 1f/32*20);
		uv[67] = new UVVertex(1f/64*48, 1f/32*20);
		// Arm Bottom UV
		uv[68] = new UVVertex(1f/64*48, 1f/32*16);
		uv[69] = new UVVertex(1f/64*52, 1f/32*16);
		uv[70] = new UVVertex(1f/64*48, 1f/32*20);
		uv[71] = new UVVertex(1f/64*52, 1f/32*20);
		
		// Leg Front UV
		uv[72] = new UVVertex(1f/64*4, 1f/32*20);
		uv[73] = new UVVertex(1f/64*8, 1f/32*20);
		uv[74] = new UVVertex(1f/64*4, 1f/32*32);
		uv[75] = new UVVertex(1f/64*8, 1f/32*32);
		// Leg Left UV
		uv[76] = new UVVertex(1f/64*0, 1f/32*20);
		uv[77] = new UVVertex(1f/64*4, 1f/32*20);
		uv[78] = new UVVertex(1f/64*0, 1f/32*32);
		uv[79] = new UVVertex(1f/64*4, 1f/32*32);
		// Leg Right UV
		uv[80] = new UVVertex(1f/64*8, 1f/32*20);
		uv[81] = new UVVertex(1f/64*12, 1f/32*20);
		uv[82] = new UVVertex(1f/64*8, 1f/32*32);
		uv[83] = new UVVertex(1f/64*12, 1f/32*32);
		// Leg Back UV
		uv[84] = new UVVertex(1f/64*12, 1f/32*20);
		uv[85] = new UVVertex(1f/64*16, 1f/32*20);
		uv[86] = new UVVertex(1f/64*12, 1f/32*32);
		uv[87] = new UVVertex(1f/64*16, 1f/32*32);
		// Leg Top UV
		uv[88] = new UVVertex(1f/64*4, 1f/32*16);
		uv[89] = new UVVertex(1f/64*8, 1f/32*16);
		uv[90] = new UVVertex(1f/64*4, 1f/32*20);
		uv[91] = new UVVertex(1f/64*8, 1f/32*20);
		// Leg Bottom UV
		uv[92] = new UVVertex(1f/64*8, 1f/32*16);
		uv[93] = new UVVertex(1f/64*12, 1f/32*16);
		uv[94] = new UVVertex(1f/64*8, 1f/32*20);
		uv[95] = new UVVertex(1f/64*12, 1f/32*20);
	}
	public void getMesh()
	{	
		p = new Polygon[72];
		// Head
		p[0] = new Polygon(new Vertex[] {v[0], v[1], v[4]}, new UVVertex[] {uv[2], uv[3], uv[0]});
		p[1] = new Polygon(new Vertex[] {v[1], v[5], v[4]}, new UVVertex[] {uv[3], uv[1], uv[0]});
		p[2] = new Polygon(new Vertex[] {v[1], v[2], v[5]}, new UVVertex[] {uv[10], uv[11], uv[8]});
		p[3] = new Polygon(new Vertex[] {v[2], v[6], v[5]}, new UVVertex[] {uv[11], uv[9], uv[8]});
		p[4] = new Polygon(new Vertex[] {v[2], v[3], v[6]}, new UVVertex[] {uv[14], uv[15], uv[12]});
		p[5] = new Polygon(new Vertex[] {v[3], v[7], v[6]}, new UVVertex[] {uv[15], uv[13], uv[12]});
		p[6] = new Polygon(new Vertex[] {v[3], v[0], v[7]}, new UVVertex[] {uv[6], uv[7], uv[4]});
		p[7] = new Polygon(new Vertex[] {v[0], v[4], v[7]}, new UVVertex[] {uv[7], uv[5], uv[4]});
		p[8] = new Polygon(new Vertex[] {v[4], v[5], v[7]}, new UVVertex[] {uv[18], uv[19], uv[16]});
		p[9] = new Polygon(new Vertex[] {v[5], v[6], v[7]}, new UVVertex[] {uv[19], uv[17], uv[16]});
		p[10] = new Polygon(new Vertex[] {v[3], v[2], v[0]}, new UVVertex[] {uv[22], uv[23], uv[20]});
		p[11] = new Polygon(new Vertex[] {v[2], v[1], v[0]}, new UVVertex[] {uv[23], uv[21], uv[20]});
		// Body
		p[12] = new Polygon(new Vertex[] {v[8], v[9], v[12]}, new UVVertex[] {uv[26], uv[27], uv[24]});
		p[13] = new Polygon(new Vertex[] {v[9], v[13], v[12]}, new UVVertex[] {uv[27], uv[25], uv[24]});
		p[14] = new Polygon(new Vertex[] {v[9], v[10], v[13]}, new UVVertex[] {uv[34], uv[35], uv[32]});
		p[15] = new Polygon(new Vertex[] {v[10], v[14], v[13]}, new UVVertex[] {uv[35], uv[33], uv[32]});
		p[16] = new Polygon(new Vertex[] {v[10], v[11], v[14]}, new UVVertex[] {uv[38], uv[39], uv[36]});
		p[17] = new Polygon(new Vertex[] {v[11], v[15], v[14]}, new UVVertex[] {uv[39], uv[37], uv[36]});
		p[18] = new Polygon(new Vertex[] {v[11], v[8], v[15]}, new UVVertex[] {uv[30], uv[31], uv[28]});
		p[19] = new Polygon(new Vertex[] {v[8], v[12], v[15]}, new UVVertex[] {uv[31], uv[29], uv[28]});
		p[20] = new Polygon(new Vertex[] {v[12], v[13], v[15]}, new UVVertex[] {uv[42], uv[43], uv[40]});
		p[21] = new Polygon(new Vertex[] {v[13], v[14], v[15]}, new UVVertex[] {uv[43], uv[41], uv[40]});
		p[22] = new Polygon(new Vertex[] {v[11], v[10], v[8]}, new UVVertex[] {uv[46], uv[47], uv[44]});
		p[23] = new Polygon(new Vertex[] {v[10], v[9], v[8]}, new UVVertex[] {uv[47], uv[45], uv[44]});
		// Left Arm
		p[24] = new Polygon(new Vertex[] {v[16], v[17], v[20]}, new UVVertex[] {uv[50], uv[51], uv[48]});
		p[25] = new Polygon(new Vertex[] {v[17], v[21], v[20]}, new UVVertex[] {uv[51], uv[49], uv[48]});
		p[26] = new Polygon(new Vertex[] {v[17], v[18], v[21]}, new UVVertex[] {uv[58], uv[59], uv[56]});
		p[27] = new Polygon(new Vertex[] {v[18], v[22], v[21]}, new UVVertex[] {uv[59], uv[57], uv[56]});
		p[28] = new Polygon(new Vertex[] {v[18], v[19], v[22]}, new UVVertex[] {uv[62], uv[63], uv[60]});
		p[29] = new Polygon(new Vertex[] {v[19], v[23], v[22]}, new UVVertex[] {uv[63], uv[61], uv[60]});
		p[30] = new Polygon(new Vertex[] {v[19], v[16], v[23]}, new UVVertex[] {uv[54], uv[55], uv[52]});
		p[31] = new Polygon(new Vertex[] {v[16], v[20], v[23]}, new UVVertex[] {uv[55], uv[53], uv[52]});
		p[32] = new Polygon(new Vertex[] {v[20], v[21], v[23]}, new UVVertex[] {uv[66], uv[67], uv[64]});
		p[33] = new Polygon(new Vertex[] {v[21], v[22], v[23]}, new UVVertex[] {uv[67], uv[65], uv[64]});
		p[34] = new Polygon(new Vertex[] {v[19], v[18], v[16]}, new UVVertex[] {uv[70], uv[71], uv[68]});
		p[35] = new Polygon(new Vertex[] {v[18], v[17], v[16]}, new UVVertex[] {uv[71], uv[69], uv[68]});
		// Right Arm
		p[36] = new Polygon(new Vertex[] {v[24], v[25], v[28]}, new UVVertex[] {uv[50], uv[51], uv[48]});
		p[37] = new Polygon(new Vertex[] {v[25], v[29], v[28]}, new UVVertex[] {uv[51], uv[49], uv[48]});
		p[38] = new Polygon(new Vertex[] {v[25], v[26], v[29]}, new UVVertex[] {uv[58], uv[59], uv[56]});
		p[39] = new Polygon(new Vertex[] {v[26], v[30], v[29]}, new UVVertex[] {uv[59], uv[57], uv[56]});
		p[40] = new Polygon(new Vertex[] {v[26], v[27], v[30]}, new UVVertex[] {uv[62], uv[63], uv[60]});
		p[41] = new Polygon(new Vertex[] {v[27], v[31], v[30]}, new UVVertex[] {uv[63], uv[61], uv[60]});
		p[42] = new Polygon(new Vertex[] {v[27], v[24], v[31]}, new UVVertex[] {uv[54], uv[55], uv[52]});
		p[43] = new Polygon(new Vertex[] {v[24], v[28], v[31]}, new UVVertex[] {uv[55], uv[53], uv[52]});
		p[44] = new Polygon(new Vertex[] {v[28], v[29], v[31]}, new UVVertex[] {uv[66], uv[67], uv[64]});
		p[45] = new Polygon(new Vertex[] {v[29], v[30], v[31]}, new UVVertex[] {uv[67], uv[65], uv[64]});
		p[46] = new Polygon(new Vertex[] {v[27], v[26], v[24]}, new UVVertex[] {uv[70], uv[71], uv[68]});
		p[47] = new Polygon(new Vertex[] {v[26], v[25], v[24]}, new UVVertex[] {uv[71], uv[69], uv[68]});
		// Left Leg
		p[48] = new Polygon(new Vertex[] {v[32], v[33], v[36]}, new UVVertex[] {uv[74], uv[75], uv[72]});
		p[49] = new Polygon(new Vertex[] {v[33], v[37], v[36]}, new UVVertex[] {uv[75], uv[73], uv[72]});
		p[50] = new Polygon(new Vertex[] {v[33], v[34], v[37]}, new UVVertex[] {uv[82], uv[83], uv[80]});
		p[51] = new Polygon(new Vertex[] {v[34], v[38], v[37]}, new UVVertex[] {uv[83], uv[81], uv[80]});
		p[52] = new Polygon(new Vertex[] {v[34], v[35], v[38]}, new UVVertex[] {uv[86], uv[87], uv[84]});
		p[53] = new Polygon(new Vertex[] {v[35], v[39], v[38]}, new UVVertex[] {uv[87], uv[85], uv[84]});
		p[54] = new Polygon(new Vertex[] {v[35], v[32], v[39]}, new UVVertex[] {uv[78], uv[79], uv[76]});
		p[55] = new Polygon(new Vertex[] {v[32], v[36], v[39]}, new UVVertex[] {uv[79], uv[77], uv[76]});
		p[56] = new Polygon(new Vertex[] {v[36], v[37], v[39]}, new UVVertex[] {uv[90], uv[91], uv[88]});
		p[57] = new Polygon(new Vertex[] {v[37], v[38], v[39]}, new UVVertex[] {uv[91], uv[89], uv[88]});
		p[58] = new Polygon(new Vertex[] {v[35], v[34], v[32]}, new UVVertex[] {uv[94], uv[95], uv[92]});
		p[59] = new Polygon(new Vertex[] {v[34], v[33], v[32]}, new UVVertex[] {uv[95], uv[93], uv[92]});
		// Right Leg
		p[60] = new Polygon(new Vertex[] {v[40], v[41], v[44]}, new UVVertex[] {uv[74], uv[75], uv[72]});
		p[61] = new Polygon(new Vertex[] {v[41], v[45], v[44]}, new UVVertex[] {uv[75], uv[73], uv[72]});
		p[62] = new Polygon(new Vertex[] {v[41], v[42], v[45]}, new UVVertex[] {uv[82], uv[83], uv[80]});
		p[63] = new Polygon(new Vertex[] {v[42], v[46], v[45]}, new UVVertex[] {uv[83], uv[81], uv[80]});
		p[64] = new Polygon(new Vertex[] {v[42], v[43], v[46]}, new UVVertex[] {uv[86], uv[87], uv[84]});
		p[65] = new Polygon(new Vertex[] {v[43], v[47], v[46]}, new UVVertex[] {uv[87], uv[85], uv[84]});
		p[66] = new Polygon(new Vertex[] {v[43], v[40], v[47]}, new UVVertex[] {uv[78], uv[79], uv[76]});
		p[67] = new Polygon(new Vertex[] {v[40], v[44], v[47]}, new UVVertex[] {uv[79], uv[77], uv[76]});
		p[68] = new Polygon(new Vertex[] {v[44], v[45], v[47]}, new UVVertex[] {uv[90], uv[91], uv[88]});
		p[69] = new Polygon(new Vertex[] {v[45], v[46], v[47]}, new UVVertex[] {uv[91], uv[89], uv[88]});
		p[70] = new Polygon(new Vertex[] {v[43], v[42], v[40]}, new UVVertex[] {uv[94], uv[95], uv[92]});
		p[71] = new Polygon(new Vertex[] {v[42], v[41], v[40]}, new UVVertex[] {uv[95], uv[93], uv[92]});
		mesh = p;

		p[0] = new Polygon(new Vertex[] {v[0], v[1], v[4]}, new UVVertex[] {uv[2], uv[3], uv[0]});
		p[1] = new Polygon(new Vertex[] {v[1], v[5], v[4]}, new UVVertex[] {uv[3], uv[1], uv[0]});
		p[2] = new Polygon(new Vertex[] {v[1], v[2], v[5]}, new UVVertex[] {uv[10], uv[11], uv[8]});
		p[3] = new Polygon(new Vertex[] {v[2], v[6], v[5]}, new UVVertex[] {uv[11], uv[9], uv[8]});
		p[4] = new Polygon(new Vertex[] {v[2], v[3], v[6]}, new UVVertex[] {uv[14], uv[15], uv[12]});
		p[5] = new Polygon(new Vertex[] {v[3], v[7], v[6]}, new UVVertex[] {uv[15], uv[13], uv[12]});
		p[6] = new Polygon(new Vertex[] {v[3], v[0], v[7]}, new UVVertex[] {uv[6], uv[7], uv[4]});
		p[7] = new Polygon(new Vertex[] {v[0], v[4], v[7]}, new UVVertex[] {uv[7], uv[5], uv[4]});
		p[8] = new Polygon(new Vertex[] {v[4], v[5], v[7]}, new UVVertex[] {uv[18], uv[19], uv[16]});
		p[9] = new Polygon(new Vertex[] {v[5], v[6], v[7]}, new UVVertex[] {uv[19], uv[17], uv[16]});
		p[10] = new Polygon(new Vertex[] {v[3], v[2], v[0]}, new UVVertex[] {uv[22], uv[23], uv[20]});
		p[11] = new Polygon(new Vertex[] {v[2], v[1], v[0]}, new UVVertex[] {uv[23], uv[21], uv[20]});
	}
	
}
