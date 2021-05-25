package book.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.book.model.Book;

import book.BookService;
import book.helper.RetrofitClientCreator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Book_UI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel de;
	private JLabel lbNewLabel;
	private JTextField txtId;
	private JLabel lblName;
	private JTextField txtName;
	private JLabel lblTitle;
	private JTextField txtTitle;
	private JLabel lblPrice;
	private JTextField txtPrice;
	private JLabel lblQuantity;
	private JTextField txtQuantity;
	private JLabel lblDescription;
	private JTextField txtDescription;
	
	private JButton btnAdd, btnRemove, btnGet, btnEdit, btnExit, btnGetById, btnClear;
	private Retrofit retrofit;
	private BookService bookService;
	private JTextField txtIdBookSearch;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				try {
					Book_UI frame = new Book_UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
	}
	
	public Book_UI() {
		retrofit = RetrofitClientCreator.getClient();
		bookService = retrofit.create(BookService.class);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,800,500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 750, 200);
		contentPane.add(scrollPane);
		de = new DefaultTableModel(new Object[][] {},
				new String[] {"id","name","title","price","quantity","description"});
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String id = (String) table.getValueAt(table.getSelectedRow(), 0);
				txtId.setText(id);
				String name = (String) table.getValueAt(table.getSelectedRow(), 1);
				txtName.setText(name);
				String title = (String) table.getValueAt(table.getSelectedRow(), 2);
				txtTitle.setText(title);
				String price = (String) table.getValueAt(table.getSelectedRow(), 3);
				txtPrice.setText(price);
				String quantity = (String) table.getValueAt(table.getSelectedRow(), 4);
				txtQuantity.setText(quantity);
				String description = (String) table.getValueAt(table.getSelectedRow(), 5);
				txtDescription.setText(description);
			}
		});
		
		table.setModel(de);
		scrollPane.setViewportView(table);
		
		lbNewLabel = new JLabel("ID");
		lbNewLabel.setBounds(20, 230, 67, 21);
		contentPane.add(lbNewLabel);
		
		txtId = new JTextField();
		txtId.setBounds(90, 230, 150, 21);
		contentPane.add(txtId);
		txtId.setColumns(10);
		
		lblName = new JLabel("Name");
		lblName.setBounds(20, 255, 90, 21);
		contentPane.add(lblName);
		
		txtName = new JTextField();
		txtName.setBounds(90, 255, 150, 21);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		lblTitle = new JLabel("Title");
		lblTitle.setBounds(20, 280, 67, 21);
		contentPane.add(lblTitle);
		
		txtTitle = new JTextField();
		txtTitle.setBounds(90, 280, 150, 21);
		contentPane.add(txtTitle);
		txtTitle.setColumns(10);
		
		lblPrice = new JLabel("Price");
		lblPrice.setBounds(20, 305, 67, 21);
		contentPane.add(lblPrice);
		
		txtPrice = new JTextField();
		txtPrice.setBounds(90, 305, 150, 21);
		contentPane.add(txtPrice);
		txtPrice.setColumns(10);
		
		
		lblQuantity = new JLabel("Quantity");
		lblQuantity.setBounds(20, 330, 67, 21);
		contentPane.add(lblQuantity);
		
		txtQuantity = new JTextField();
		txtQuantity.setBounds(90, 330, 150, 21);
		contentPane.add(txtQuantity);
		txtQuantity.setColumns(10);
		
		lblDescription = new JLabel("Description");
		lblDescription.setBounds(20, 360, 67, 21);
		contentPane.add(lblDescription);
		
		txtDescription = new JTextField();
		txtDescription.setBounds(90, 360, 150, 21);
		contentPane.add(txtDescription);
		txtDescription.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					bookService.addBook(new Book(Integer.parseInt(txtId.getText()), txtName.getText(),
							txtTitle.getText(), txtDescription.getText(), Integer.parseInt(txtPrice.getText()), 
							Integer.parseInt(txtQuantity.getText()))).enqueue(new Callback<Void>() {
								
								@Override
								public void onResponse(Call<Void> call, Response<Void> response) {
									getAll();
									
								}
								
								

								@Override
								public void onFailure(Call<Void> call, Throwable t) {
									// TODO Auto-generated method stub
									
								}
							});
							
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "sai dữ liệu xin nhập đúng và đủ");
				}
				
			}
		});
		btnAdd.setBounds(300, 230, 120, 30);
		contentPane.add(btnAdd);
		
		btnGet = new JButton("Get All");
		btnGet.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getAll();
				
			}
		});
		btnGet.setBounds(300, 270, 120, 30);
		contentPane.add(btnGet);
		
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					bookService.removeBook(Integer.parseInt(txtId.getText().trim()))
							.enqueue(new Callback<Void>() {
								
								@Override
								public void onResponse(Call<Void> call, Response<Void> response) {
									getAll();
									
								}
								
								

								@Override
								public void onFailure(Call<Void> call, Throwable t) {
									// TODO Auto-generated method stub
									
								}
							});
							
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "sai dữ liệu xin nhập đúng và đủ");
				}
				
			}
		});
		btnRemove.setBounds(300, 310, 120, 30);
		contentPane.add(btnRemove);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					bookService.editBook(new Book(Integer.parseInt(txtId.getText()), txtName.getText(),
							txtTitle.getText(), txtDescription.getText(), Integer.parseInt(txtPrice.getText()), 
							Integer.parseInt(txtQuantity.getText()))).enqueue(new Callback<Void>() {
								
								@Override
								public void onResponse(Call<Void> call, Response<Void> response) {
									getAll();
									
								}
								
								

								@Override
								public void onFailure(Call<Void> call, Throwable t) {
									// TODO Auto-generated method stub
									
								}
							});
							
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "sai dữ liệu xin nhập đúng và đủ");
				}
				
			}
		});
		btnEdit.setBounds(300, 350, 120, 30);
		contentPane.add(btnEdit);
		
		
		btnGetById = new JButton("Get By id");
		btnGetById.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					bookService.getBookById(Integer.parseInt(txtIdBookSearch.getText()))
							.enqueue(new Callback<Book>() {

								@Override
								public void onResponse(Call<Book> call, Response<Book> response) {
									JOptionPane.showMessageDialog(null,"Id: " + response.body().getId()+
										"Name: " + response.body().getName() + 
										"Title: " + response.body().getTitle() +
										"Price: " + response.body().getPrice() +
										"Quantity: " + response.body().getQuantity() +
										"Description: " + response.body().getDescription());
									
								}

								@Override
								public void onFailure(Call<Book> call, Throwable t) {
									JOptionPane.showMessageDialog(null, "lỗi");
									
								}
								
								
							});
							
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "sai dữ liệu xin nhập đúng và đủ");
				}
				
			}
		});
		btnGetById.setBounds(300, 390, 120, 30);
		contentPane.add(btnGetById);
		
		txtIdBookSearch = new JTextField();
		txtIdBookSearch.setBounds(300, 420, 120, 30);
		contentPane.add(txtIdBookSearch);
		txtIdBookSearch.setColumns(10);
		
		btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				txtId.setText("");
				txtName.setText("");
				txtTitle.setText("");
				txtPrice.setText("");
				txtDescription.setText("");
				txtQuantity.setText("");
				txtIdBookSearch.setText("");
				
			}
		});
		
		btnClear.setBounds(600, 250, 120, 30);
		contentPane.add(btnClear);
	}
	
	public void getAll() {
		de.getDataVector().removeAllElements();
		Call<List<Book>> call = bookService.getBook();
		
		try {
			Response<List<Book>> response;
			response = call.execute();
			response.body().forEach(x->{
				Vector<String> vector = new Vector<String>();
				vector.addElement(String.valueOf(x.getId()));
				vector.addElement(String.valueOf(x.getName()));
				vector.addElement(String.valueOf(x.getTitle()));
				vector.addElement(String.valueOf(x.getPrice()));
				vector.addElement(String.valueOf(x.getQuantity()));
				vector.addElement(String.valueOf(x.getDescription()));
				de.addRow(vector);
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}
